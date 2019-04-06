package azzukunj.cottonuniversity.healthandfitnesskunjazzu;








import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;

import android.widget.TextView;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;


public class sitUps extends Activity implements SensorEventListener  {

    public SensorManager sensorManager;
    SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
    String id=sp.getString("id","Error");
    String xage=sp.getString(id+"age","Error");
    String xweight=sp.getString(id+"weight","Error");
    int age=Integer.parseInt(xage);
    int weight=Integer.parseInt(xweight);;
    double calorieburnt;
    calorie CALORIE=new calorie(age,weight);





  public static TextView z,caloriedisp;
    public int reps=5;
    public int i=0;
    public int c=0;


  public static  String sx, sy, sz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_ups);


        z = (TextView) findViewById (R.id.repsxml);
        z = (TextView) findViewById (R.id.caloriedispxml);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        calorieburnt=CALORIE.calculate();


        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


            float zVal = event.values[2];




            reps = Math.round(zVal);
            if (reps > 5) {
                if (c >0)
                {
            i++;
            z.setText(Integer.toString(i));
            caloriedisp.setText(Double.toString(calorieburnt));
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
