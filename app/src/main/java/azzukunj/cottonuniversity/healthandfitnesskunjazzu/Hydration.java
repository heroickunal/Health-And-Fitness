package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class Hydration extends AppCompatActivity {
private TextView textView;
private Handler mhandler=new Handler();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydration);
        textView=findViewById(R.id.onoff);

        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String statushydration=sp.getString("statushydration","000");
        textView.setText(statushydration);


    }
      /*  public void on (View v){
           SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);


            SharedPreferences.Editor editor=sp.edit();
            editor.putString("statushydration","ON");

            editor.putString("hydration","1");
            editor.apply();
            String statushydration=sp.getString("statushydration","OFF");
            textView.setText(statushydration);

           //Intent serviceIntent = new Intent(this,jobintent.class);
          // serviceIntent.putExtra("inputExtra", 1000);

          // jobintent.enqueueWork(this, serviceIntent);


        }*/

    public void off(View v)
    {
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("hydration","0");
        editor.putString("statushydration","OFF");
        editor.apply();

        String statushydration=sp.getString("statushydration","000");
        textView.setText("STATUS "+statushydration);
mhandler.removeCallbacks(mToastRunnable);
    }

    public void on(View v) {

        mToastRunnable.run();
    }
    private Runnable mToastRunnable=new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            try {
                SharedPreferences sp = getSharedPreferences("preferences", MODE_PRIVATE);

                String loop = sp.getString("", "statushydration");



                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("statushydration", "ON");
                    editor.apply();

                    editor.putString("hydration", "1");
                    editor.apply();
                    String statushydration = sp.getString("statushydration", "OFF");
                    textView.setText("STATUS "+statushydration);


                    Intent notificationIntent = new Intent(Hydration.this, AlarmReceiver.class);
                    PendingIntent broadcast = PendingIntent.getBroadcast(Hydration.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    Calendar cal = Calendar.getInstance();
                    //cal.add(Calendar.SECOND, 5);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES,broadcast);                    mhandler.postDelayed(this, 5000);
                }



            catch (Exception e)
            {
            }

        }
    };

    }
