package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // final Button GetServerData = (Button) findViewById(R.id.GetServerData);


        // On button click call this listener
        // GetServerData.setOnClickListener(new View.OnClickListener() {
            /*public void onClick(View v) {

                Toast.makeText(getBaseContext(),
                        "Please wait, connecting to server.",
                        Toast.LENGTH_SHORT).show();*/


        // Create Inner Thread Class
        Button situps,pushups,C,m1,m2;

        situps = findViewById(R.id.su);
        m1 = findViewById(R.id.d);
        m2 = findViewById(R.id.e);

        pushups= findViewById(R.id.pu);
        C = findViewById(R.id.c);


        situps.setOnClickListener(this);
        pushups.setOnClickListener(this);
        C.setOnClickListener(this);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.su:
                Thread background = new Thread(new Runnable() {


                    public void run() {
                        try {
                            Intent i1=new Intent(MainActivity.this,sitUps.class);
                            startActivity(i1);



                        } catch (Throwable t) {
                            Log.i("Animation", "Thread  exception " + t);
                        }
                    }





                });
                background.start();


                break;
            case R.id.pu:
                Intent i2=new Intent(MainActivity.this,pushUps.class);
                startActivity(i2);
                break;
            case R.id.c:
                Intent i3=new Intent(MainActivity.this,calculator.class);
                startActivity(i3);
                break;
            case R.id.d:
                Intent i4=new Intent(MainActivity.this,Pedometer.class);
                startActivity(i4);
                break;
            case R.id.e:
                Intent i5=new Intent(MainActivity.this,m2.class);
                startActivity(i5);
                break;

        }
    }

}





