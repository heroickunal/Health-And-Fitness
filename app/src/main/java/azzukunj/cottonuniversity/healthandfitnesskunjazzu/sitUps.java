package azzukunj.cottonuniversity.healthandfitnesskunjazzu;








import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.converttoless;

import static android.widget.Toast.LENGTH_SHORT;


public class sitUps extends Activity implements SensorEventListener  {

    public SensorManager sensorManager;


    calorie CALORIE=new calorie();



  public static TextView z,caloriedisp;
    public int reps=5;
    public int i=0,countdown=0,totalcal;
    public int c=0,get;
    Double calorieburnt=0.0;
    static int age,weight;

    static String xage,xweight,s,time,dd;
    ProgressBar bar;

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("HH:mm");
    SimpleDateFormat date=new SimpleDateFormat("dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_ups);


        z = (TextView) findViewById (R.id.caloriedispxml);
        caloriedisp = (TextView) findViewById (R.id.tv_steps);
        bar=findViewById(R.id.barxml);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);







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
    public void onAccuracyChanged(Sensor arg0, int arg1) {


    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


            float zVal = event.values[2];




            reps = Math.round(zVal);
            if (reps > 5) {
                if (c >0)
                {





                    i++;
                    z.setText(Integer.toString(i));

                    calorieburnt=calorieburnt+CALORIE.calculate(age,weight);
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






                    editor.putString("r",Integer.toString(plot));
                    editor.apply();



                    editor.putString("add or not",Integer.toString(1));
                    editor.apply();

                    /*String t=sp.getString("totalcal","0");
                    totalcal=Integer.parseInt(t);
                    totalcal=plot+totalcal;
                    editor.putString("totalcal",Integer.toString(totalcal));
                    editor.commit();
                    totalcal=0;
*/


                    c = 0;
        }
            }
            if(reps<6&&c<1)
            {
                c=1;
            }
        }
    }

}