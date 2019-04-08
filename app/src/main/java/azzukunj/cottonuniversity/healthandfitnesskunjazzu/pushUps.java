package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.converttoless;


import static android.widget.Toast.LENGTH_SHORT;

public class pushUps extends AppCompatActivity implements SensorEventListener {
    calorie CALORIE=new calorie();
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;
    TextView reps,caloriedisp;
    public int i=0,totalcal;
    //TextView xx=(TextView)findViewById(R.id.p);
    //String c="CLOSE";
    //String f="FAR";
    Double calorieburnt=0.0;
    String s,xage,xweight,time,dd;
    int age,weight,get;

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("HH:mm");
    SimpleDateFormat date=new SimpleDateFormat("dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_ups);



        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        reps=findViewById(R.id.caloriedispxml);

        caloriedisp = (TextView) findViewById (R.id.tv_steps);


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

        time=format.format(calendar.getTime());
        dd=date.format(calendar.getTime());

        get=toMins(time);


    }
    public static int toMins(String s)
    {
        String[] hourmin=s.split(":");
        int hour=Integer.parseInt(hourmin[0]);
        int mins=Integer.parseInt(hourmin[1]);
        int hoursInMins=hour*60;
        return hoursInMins+mins;


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                //near
                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                //xx.setText(c);
                i++;
                reps.setText(Integer.toString(i));
                calorieburnt=calorieburnt+CALORIE.calculate(age,weight)+0.2;
                s=converttoless.convert(calorieburnt);
                caloriedisp.setText(s);





                SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
                int plot = (int)calorieburnt.doubleValue();
                SharedPreferences.Editor editor=sp.edit();
                String getx=Integer.toString(get);
                editor.putString(getx+"day",Integer.toString(plot));
                editor.apply();


                editor.putString(dd+"week",Integer.toString(plot));
                editor.apply();
/*
                String t=sp.getString("totalcal","0");
                totalcal=Integer.parseInt(t);
                totalcal=plot+totalcal;
                editor.putString("totalcal",Integer.toString(totalcal));
                editor.commit();
*/





                editor.putString("r",Integer.toString(plot));
                editor.apply();


                editor.putString("add or not",Integer.toString(1));
                editor.apply();

            } else {
                //far
                Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();
                //xx.setText(f);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

