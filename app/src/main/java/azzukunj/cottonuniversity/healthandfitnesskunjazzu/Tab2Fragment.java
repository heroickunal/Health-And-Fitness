package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static android.content.Context.MODE_PRIVATE;

public class Tab2Fragment extends Fragment {
    LineGraphSeries<DataPoint> graphSeries;
    GraphView graphView;
    double x=0,y=0;
    TextView totalcal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        totalcal=view.findViewById(R.id.totalcaloriesxml);
        graphView=view.findViewById(R.id.weekgraphxml);

        graphSeries=new LineGraphSeries<DataPoint>();
        SharedPreferences sp=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);

        for(int i=1;i<30;i++)
        {            String id=sp.getString("id","ppppppppppppp");

            if(i>=1&&i<=9) {
                String details = sp.getString(id+"0" + Integer.toString(i) + "week", "0");
                int y = Integer.parseInt(details);
                graphSeries.appendData(new DataPoint(i, y), true, 30);
            }
            else
            {
                String details = sp.getString( id+Integer.toString(i) + "week", "0");
                int y = Integer.parseInt(details);
                graphSeries.appendData(new DataPoint(i, y), true, 30);
            }


        }
        graphView.addSeries(graphSeries);


        String addornot=sp.getString("add or not","0");
        if(Integer.parseInt(addornot)==1) {
            String id=sp.getString("id","ppppppppppppp");
            String tc=sp.getString(id+"r","0");
            String tx=sp.getString(id+"totalcal","0");
            //totalcal.setText(tc);

            int x=Integer.parseInt(tc);
            int y=Integer.parseInt(tx);

            int total = x + y;

            SharedPreferences.Editor editor = sp.edit();

            editor.putString(id+"totalcal", Integer.toString(total));
            editor.apply();
            totalcal.setText(Integer.toString(total));
            editor.putString("add or not","0");
            editor.apply();
        }
        else {
            String id=sp.getString("id","ppppppppppppp");

            String tx = sp.getString(id+"totalcal", "0");

            totalcal.setText((tx));

        }

        return view;
    }
}
