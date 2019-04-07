package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.converttoless;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        tv_steps = findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        caloriedisp = (TextView) findViewById (R.id.caloriedispxml);


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
            tv_steps.setText(String.valueOf(event.values[0]));


            calorieburnt=calorieburnt+CALORIE.calculate(age,weight)-0.2;
            s=converttoless.convert(calorieburnt);
            caloriedisp.setText(s);



            time=format.format(calendar.getTime());
            dd=date.format(calendar.getTime());

            get=toMins(time);



            SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
            double x=calorieburnt.doubleValue();

            int plot = (int)x;
            SharedPreferences.Editor editor=sp.edit();
            String getx=Integer.toString(get);
            editor.putString(getx+"day",Integer.toString(plot));
            editor.apply();


            editor.putString(dd+"week",Integer.toString(plot));
            editor.apply();


            editor.putString("r",Integer.toString(plot));
            editor.apply();

            editor.putString("add or not",Integer.toString(1));
            editor.apply();
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
}