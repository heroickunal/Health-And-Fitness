package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity{


    private EditText logemail,logpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logemail=findViewById(R.id.logemail);
        logpass=findViewById(R.id.logpass1);
        SharedPreferences sp1=getSharedPreferences("preferences",MODE_PRIVATE);
        String countdetail=sp1.getString("detailcount","1");


        if(countdetail==("0")) {
            Intent workout = new Intent(MainActivity.this, Login.class);
            startActivity(workout);
        }

    }
   public void signup(View v)         //ON CLICKING BUTTON SIGNUP, START SIGNUP ACTIVITY
    {

            Intent signup = new Intent(MainActivity.this, Signup.class);
            startActivity(signup);

    }

    public void login(View v)           //LOGIN BUTTON
    {
        String email=logemail.getText().toString();
        String pass=logpass.getText().toString();
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String details=sp.getString(email+pass,"Email or Password is incorrect");   //IF THE ID AND PASSWORD IS CORRECT, WELCOME THE USER
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("id",details);                                                      //STORE THE USER INFO TO KEY welcome, AND START THE GREETING ACTIVITY
        editor.commit();
        if(details!=("Email or Password is incorrect")) {
            Intent welcome = new Intent(MainActivity.this, Login.class);
            startActivity(welcome);
        }
        else {

                  Toast.makeText(getApplicationContext(),"Email or Password wrong",LENGTH_SHORT).show();
        }






}}


















