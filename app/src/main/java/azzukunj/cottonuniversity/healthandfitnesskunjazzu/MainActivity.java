package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends Activity {


    private EditText logemail,logpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logemail=findViewById(R.id.logemail1);
        logpass=findViewById(R.id.logpass1);
        SharedPreferences sp1=getSharedPreferences("preferences",MODE_PRIVATE);
        String countdetail=sp1.getString("detailcount","1");


        if(countdetail==("0")) {
            Intent workout = new Intent(MainActivity.this, Login.class);
            startActivity(workout);
        }

    }
   public void signup(View v)
    {

            Intent signup = new Intent(MainActivity.this, Signup.class);
            startActivity(signup);

    }

    public void login(View v)
    {
        String email=logemail.getText().toString();
        String pass=logpass.getText().toString();
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String details=sp.getString(email+pass,"Email or Password is incorrect");   //IF THE ID AND PASSWORD IS CORRECT, WELCOME THE USER
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("id",details);                                                      //STORE THE USER INFO TO KEY welcome, AND START THE GREETING ACTIVITY
        editor.commit();
        if(details!=("Email or Password is incorrect")) {
            Intent welcome = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(welcome);
        }
        else {

                  Toast.makeText(getApplicationContext(),"Email or Password wrong",LENGTH_SHORT).show();
        }

    }}


















