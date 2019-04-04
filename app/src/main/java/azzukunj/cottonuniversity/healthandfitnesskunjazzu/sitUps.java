package azzukunj.cottonuniversity.healthandfitnesskunjazzu;








import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
//import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class sitUps extends Activity implements SensorEventListener  {

    public SensorManager sensorManager;

   //public static TextView x;
  // public static TextView y;
  public static TextView z;
    public int reps=5;
    public int i=0;
    public int c=0;


  public static  String sx, sy, sz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_ups);

       // x = (TextView) findViewById (R.id.textView2);
        //y = (TextView) findViewById (R.id.textView3);
        z = (TextView) findViewById (R.id.textView4);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*  @Override
      public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.activity_main, menu);
          return true;
      }
  */
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            //  float xVal = event.values[0];
            // float yVal = event.values[1];
            float zVal = event.values[2];


            //  sx = "X Value : <font color = '#800080'> " + xVal + "</font>";
            // sy = "Y Value : <font color = '#800080'> " + yVal + "</font>";
            //  sz = "Z Value : <font color = '#800080'> " + zVal + "</font>";

            //x.setText(Html.fromHtml(sx));
            // y.setText(Html.fromHtml(sy));
            // z.setText(Html.fromHtml(sz));
            reps = Math.round(zVal);
            if (reps > 5) {
                if (c >0)
                {
            i++;
            z.setText(Integer.toString(i));
            c = 0;
        }
            }
            if(reps<6&&c<1)
            {
                c=1;
            }
        }
    }
  /*  @Override
    public void onClick(View v) {
        if(v.getId()==R.id.gc)
        {
                Toast.makeText(getApplicationContext(), "Gc run successfully", Toast.LENGTH_SHORT).show();



    }}*/
}
