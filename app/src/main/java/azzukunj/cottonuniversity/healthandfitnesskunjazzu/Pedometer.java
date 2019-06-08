package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.converttoless;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.holocircularprogressbar.HoloCircularProgressBar;

import static android.widget.Toast.LENGTH_SHORT;

public class Pedometer extends AppCompatActivity  implements SensorEventListener {

    TextView tv_steps,caloriedisp;
    SensorManager sensorManager;
    boolean running = false;
    Double calorieburnt=0.0;
    String s,xage,xweight,time,dd;
    int age,weight,get,totalcal;

    calorie CALORIE=new calorie();
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("HH:mm");
    SimpleDateFormat date=new SimpleDateFormat("dd");
    private HoloCircularProgressBar mHoloCircularProgressBar;



    private ObjectAnimator mProgressBarAnimator;
    private Button HeartRate,start,stop,goback;
    private static int startstop=0;

private static String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        tv_steps = findViewById(R.id.caloriedispxml);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        caloriedisp = (TextView) findViewById (R.id.tv_steps);
        HeartRate = findViewById(R.id.HeartRate);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        goback = findViewById(R.id.goback);

        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String id=sp.getString("id","Email or Password is incorrect");
        xage=sp.getString(id+"age","Email or Password is incorrect");
        xweight=sp.getString(id+"weight","Email or Password is incorrect");

        try {
            age = Integer.parseInt(xage);
            weight = Integer.parseInt(xweight);
            Toast.makeText(getApplicationContext(),"HEART RATE DEVICE NOT CONNECTED",LENGTH_SHORT).show();
        }
        catch(NumberFormatException nfe)
        {
            System.out.print("wwwwrrroooonngggggggg");
        }
        HeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(Pedometer.this, HeartRate.class);
                startActivity(I);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startstop=1;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startstop=0;
                tv_steps.setText(temp);
                caloriedisp.setText(s);
                animate(mHoloCircularProgressBar, null, 0.0f, 1000);
                mHoloCircularProgressBar.setMarkerProgress(0.0f);
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Pedometer.this,Main2Activity.class);
                startActivity(i);
            }
        });

        mHoloCircularProgressBar = (HoloCircularProgressBar) findViewById(
                R.id.holoCircularProgressBar);

        {

            mHoloCircularProgressBar.setProgressColor(Color.GREEN);

            //randomColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            mHoloCircularProgressBar.setProgressBackgroundColor(Color.WHITE);
            animate(mHoloCircularProgressBar, null, 0, 1000);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor Not Found", Toast.LENGTH_LONG);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            if (startstop == 1) {
                tv_steps.setText(String.valueOf(event.values[0]));
                temp = String.valueOf(event.values[0]);

                calorieburnt = calorieburnt + CALORIE.calculate(age, weight) - 0.2;
                s = converttoless.convert(calorieburnt);
                caloriedisp.setText(s);


                time = format.format(calendar.getTime());
                dd = date.format(calendar.getTime());

                get = toMins(time);


                SharedPreferences sp = getSharedPreferences("preferences", MODE_PRIVATE);
                double x = calorieburnt.doubleValue();
                String id=sp.getString("id","ppppppppppppp");

                int plot = (int) x;
                SharedPreferences.Editor editor = sp.edit();
                String getx = Integer.toString(get);
                editor.putString(id+getx + "day", Integer.toString(plot));
                editor.apply();


                editor.putString(id+dd + "week", Integer.toString(plot));
                editor.apply();


                editor.putString(id+"r", Integer.toString(plot));
                editor.apply();

                editor.putString("add or not", Integer.toString(1));
                editor.apply();
            }
        }
    }
    public static int toMins(String s)
    {
        String[] hourmin=s.split(":");
        int hour=Integer.parseInt(hourmin[0]);
        int mins=Integer.parseInt(hourmin[1]);
        int hoursInMins=hour*60;
        return hoursInMins+mins;


    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void animate(final HoloCircularProgressBar progressBar,
                         final Animator.AnimatorListener listener) {
        final float progress = (float) (Math.random() * 2);
        int duration = 3000;
        animate(progressBar, listener, progress, duration);
    }

    private void animate(final HoloCircularProgressBar progressBar, final Animator.AnimatorListener listener,
                         final float progress, final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
        mProgressBarAnimator.setDuration(duration);

        mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                progressBar.setProgress(progress);
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            }

            @Override
            public void onAnimationStart(final Animator animation) {
            }
        });
        if (listener != null) {
            mProgressBarAnimator.addListener(listener);
        }
        mProgressBarAnimator.reverse();
        mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                progressBar.setProgress((Float) animation.getAnimatedValue());
            }
        });
        progressBar.setMarkerProgress(progress);
        mProgressBarAnimator.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {

        return;
    }

}