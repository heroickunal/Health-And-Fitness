package azzukunj.cottonuniversity.healthandfitnesskunjazzu;





import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class calculator extends AppCompatActivity {

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("HH:mm");
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd");
    String time,date;
    int get;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        t=findViewById(R.id.txml);
        time=format.format(calendar.getTime());
        date=dateFormat.format(calendar.getTime());

        get=toMins(time);

        t.setText(Integer.toString(get));


    }
    public static int toMins(String s)
    {
        String[] hourmin=s.split(":");
        int hour=Integer.parseInt(hourmin[0]);
        int mins=Integer.parseInt(hourmin[1]);
        int hoursInMins=hour*60;
        return hoursInMins+mins;


    }

}


