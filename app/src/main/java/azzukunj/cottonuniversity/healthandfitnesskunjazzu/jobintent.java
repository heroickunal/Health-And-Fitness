package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import java.util.Calendar;

public class jobintent extends JobIntentService {
    private static final String TAG = "ExampleJobIntentService";

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, jobintent.class, 123, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleWork(@NonNull Intent intent) {



        for (int i = 0; i < 10; i++) {
            String input = intent.getStringExtra("inputExtra");

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 5);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
            if (isStopped()) return;

            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onStopCurrentWork() {

        return super.onStopCurrentWork();
    }
}
