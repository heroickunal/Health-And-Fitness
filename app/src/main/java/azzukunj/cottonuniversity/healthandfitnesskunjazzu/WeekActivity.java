package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class WeekActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> graphSeries;
    GraphView graphView;
    double x=0,y=0;
    TextView totalcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);




        totalcal=findViewById(R.id.totalcaloriesxml);
        graphView=findViewById(R.id.weekgraphxml);

        graphSeries=new LineGraphSeries<DataPoint>();
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);

        for(int i=1;i<30;i++)
        {
            if(i>=1&&i<=9) {
                String details = sp.getString("0" + Integer.toString(i) + "week", "0");
                int y = Integer.parseInt(details);
                graphSeries.appendData(new DataPoint(i, y), true, 30);
            }
            else
            {
                String details = sp.getString( Integer.toString(i) + "week", "0");
                int y = Integer.parseInt(details);
                graphSeries.appendData(new DataPoint(i, y), true, 30);
            }


        }
        graphView.addSeries(graphSeries);


        String addornot=sp.getString("add or not","0");
        if(Integer.parseInt(addornot)==1) {
            String tc=sp.getString("r","0");
            String tx=sp.getString("totalcal","0");
            //totalcal.setText(tc);

            int x=Integer.parseInt(tc);
            int y=Integer.parseInt(tx);

            int total = x + y;

            SharedPreferences.Editor editor = sp.edit();

            editor.putString("totalcal", Integer.toString(total));
            editor.apply();
            totalcal.setText(Integer.toString(total));
            editor.putString("add or not","0");
            editor.apply();
        }
        else {
            String tx = sp.getString("totalcal", "0");

            totalcal.setText((tx));

        }
    }
}
