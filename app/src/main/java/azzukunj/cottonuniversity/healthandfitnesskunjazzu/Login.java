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
        Button situps,pushups,C,m1,m2,weekactvity,hydration;

        situps = findViewById(R.id.su);
        m1 = findViewById(R.id.d);
        m2 = findViewById(R.id.e);
        weekactvity=findViewById(R.id.weekactivityxml);
        hydration=findViewById(R.id.hydration);

        pushups= findViewById(R.id.pu);
        C = findViewById(R.id.c);

       // temp = findViewById(R.id.temp);


        situps.setOnClickListener(this);
        pushups.setOnClickListener(this);
        C.setOnClickListener(this);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        weekactvity.setOnClickListener(this);
        hydration.setOnClickListener(this);

        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);

        SharedPreferences.Editor edit=sp.edit();
        edit.putString("detailcount","0");
        edit.commit();


        String id=sp.getString("id","Email or Password is incorrect");
        String t=sp.getString(id+"age","Email or Password is incorrect");




        //water importance

        SharedPreferences.Editor editor=sp.edit();
        editor.putString("111","Drinking Water Helps Maintain the Balance of Body Fluids. Your body is composed of about 60% water. The functions of these bodily fluids include digestion, absorption, circulation, creation of saliva, transportation of nutrients, and maintenance of body temperature. Through the posterior pituitary gland, your brain communicates with your kidneys and tells it how much water to excrete as urine or hold onto for reserves, says Guest, who is also an adjunct professor of medicine at Stanford University When you're low on fluids, the brain triggers the body's thirst mechanism. And unless you are taking medications that make you thirsty, Guest says, you should listen to those cues and get yourself a drink of water, juice, milk, coffee -- anything but alcohol.");
        editor.apply();

        editor.putString("112","Water Can Help Control Calories. For years, dieters have been drinking lots of water as a weight loss strategy. While water doesn't have any magical effect on weight loss, substituting it for higher calorie beverages can certainly help \n What works with weight loss is if you choose water or a non-caloric beverage over a caloric beverage and/or eat a diet higher in water-rich foods that are healthier, more filling, and help you trim calorie intake,\" says Penn State researcher Barbara Rolls, PhD, author of The Volumetrics Weight Control Plan. Food with high water content tends to look larger, its higher volume requires more chewing, and it is absorbed more slowly by the body, which helps you feel full. Water-rich foods include fruits, vegetables, broth-based soups, oatmeal, and beans.");
        editor.apply();

        editor.putString("113","Water Helps Energize Muscles. Cells that don't maintain their balance of fluids and electrolytes shrivel, which can result in muscle fatigue. \"When muscle cells don't have adequate fluids, they don't work as well and performance can suffer says Guest.Drinking enough fluids is important when exercising. Follow the American College of Sports Medicine guidelines for fluid intake before and during physical activity. These guidelines recommend that people drink about 17 ounces of fluid about two hours before exercise. During exercise, they recommend that people start drinking fluids early, and drink them at regular intervals to replace fluids lost by sweating.");
        editor.apply();



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
            case R.id.hydration:
                Intent i7=new Intent(Login.this,WeekActivity.class);
                startActivity(i7);
                break;
        }
    }

}
