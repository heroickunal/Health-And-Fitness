package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class m2 extends AppCompatActivity {

LineGraphSeries<DataPoint> graphSeries;
GraphView graphView;
double x=0,y=0;
TextView totalcal;
public int total=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);

        graphView = findViewById(R.id.graphxml);
        totalcal = findViewById(R.id.totalcaloriesxml);

        graphSeries = new LineGraphSeries<DataPoint>();
        SharedPreferences sp = getSharedPreferences("preferences", MODE_PRIVATE);
        Toast.makeText(getApplicationContext(), "Heart Rate Device Not Connected", Toast.LENGTH_LONG).show();

        //for 2pm to 3pm
        /*for(int i=50400;i<=54000;i++)
        {
            String details=sp.getString(Integer.toString(i)+"day","0");
            int y=Integer.parseInt(details);
            graphSeries.appendData(new DataPoint(i,y),true,57600);

        }*/


        //test
        for (int i = 772; i <= 782; i++) {
            String details = sp.getString(Integer.toString(i) + "day", "0");
            int y = Integer.parseInt(details);
            graphSeries.appendData(new DataPoint(i, y), true, 10);
            total = total + y;
            SharedPreferences.Editor editor = sp.edit();

            //editor.putString("totalcal",Integer.toString(total));
            //editor.apply();

        }
        graphView.addSeries(graphSeries);


        String addornot = sp.getString("add or not", "0");
        if (Integer.parseInt(addornot) == 1) {
            String tc = sp.getString("r", "0");
            String tx = sp.getString("totalcal", "0");
            //totalcal.setText(tc);

            int x = Integer.parseInt(tc);
            int y = Integer.parseInt(tx);

            int t = x + y;

            SharedPreferences.Editor editor = sp.edit();

            editor.putString("totalcal", Integer.toString(t));
            editor.apply();
            totalcal.setText(Integer.toString(t));
            editor.putString("add or not", "0");
            editor.apply();


        } else {
            String tx = sp.getString("totalcal", "0");

            totalcal.setText((tx));

        }

    }
}
