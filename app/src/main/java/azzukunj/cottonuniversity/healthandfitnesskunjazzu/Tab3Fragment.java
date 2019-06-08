package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import android.app.AlarmManager;

import static android.content.Context.MODE_PRIVATE;

public class Tab3Fragment extends Fragment {
    private TextView textView;
    private Handler mhandler=new Handler();
    private Button HydrationOn,HydrationOff;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        HydrationOn = (Button) view.findViewById(R.id.on);
        HydrationOff = (Button) view.findViewById(R.id.off);

        textView=view.findViewById(R.id.onoff);
        SharedPreferences sp=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);
        String statushydration=sp.getString("statushydration","000");
        textView.setText(statushydration);

       HydrationOn.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
                mToastRunnable.run();

            }
        });
        HydrationOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("hydration","0");
                editor.putString("statushydration","OFF");
                editor.apply();

                String statushydration=sp.getString("statushydration","000");
                textView.setText("STATUS "+statushydration);
                mhandler.removeCallbacks(mToastRunnable);

            }
        });
        return view;
    }




    private Runnable mToastRunnable=new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            try {
                SharedPreferences sp = getActivity().getSharedPreferences("preferences", MODE_PRIVATE);

                String loop = sp.getString("", "statushydration");



                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);


                SharedPreferences.Editor editor = sp.edit();
                editor.putString("statushydration", "ON");
                editor.apply();

                editor.putString("hydration", "1");
                editor.apply();
                String statushydration = sp.getString("statushydration", "OFF");
                textView.setText("STATUS "+statushydration);


                Intent notificationIntent = new Intent(getActivity().getApplication(), AlarmReceiver.class);
                PendingIntent broadcast = PendingIntent.getBroadcast(getActivity().getApplication(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
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


