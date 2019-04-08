package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity implements View.OnClickListener{
private TextView temp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // final Button GetServerData = (Button) findViewById(R.id.GetServerData);


        // On button click call this listener
        // GetServerData.setOnClickListener(new View.OnClickListener() {



        // Create Inner Thread Class
        Button situps,pushups,C,m1,m2,weekactvity;

        situps = findViewById(R.id.su);
        m1 = findViewById(R.id.d);
        m2 = findViewById(R.id.e);
        weekactvity=findViewById(R.id.weekactivityxml);

        pushups= findViewById(R.id.pu);
        C = findViewById(R.id.c);

       // temp = findViewById(R.id.temp);


        situps.setOnClickListener(this);
        pushups.setOnClickListener(this);
        C.setOnClickListener(this);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        weekactvity.setOnClickListener(this);

        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);

        SharedPreferences.Editor edit=sp.edit();
        edit.putString("detailcount","0");
        edit.commit();


        String id=sp.getString("id","Email or Password is incorrect");
        String t=sp.getString(id+"age","Email or Password is incorrect");



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.su:
                Thread background = new Thread(new Runnable() {


                    public void run() {
                        try {
                            Intent i1=new Intent(Login.this,sitUps.class);
                            startActivity(i1);



                        } catch (Throwable t) {
                            Log.i("Animation", "Thread  exception " + t);
                        }
                    }





                });
                background.start();


                break;
            case R.id.pu:
                Intent i2=new Intent(Login.this,pushUps.class);
                startActivity(i2);
                break;
            case R.id.c:
                Intent i3=new Intent(Login.this,calculator.class);
                startActivity(i3);
                break;
            case R.id.d:
                Intent i4=new Intent(Login.this,Pedometer.class);
                startActivity(i4);
                break;
            case R.id.e:
                Intent i5=new Intent(Login.this,m2.class);
                startActivity(i5);
                break;
            case R.id.weekactivityxml:
                Intent i6=new Intent(Login.this,WeekActivity.class);
                startActivity(i6);
                break;
        }
    }

}
