package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    private EditText upname,upemail,uppass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        upname=findViewById(R.id.upname);
        upemail=findViewById(R.id.upemail);
        uppass=findViewById(R.id.uppass);




    }
    public void signup(View v)
    {
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String newname=upname.getText().toString();
        String newemail=upemail.getText().toString();
        String newpass=uppass.getText().toString();
        SharedPreferences.Editor edit=sp.edit();

        edit.putString(newemail+newpass,newname+newemail);  //STORE THE USER INFO
        String id=newemail+newpass;

        SharedPreferences.Editor editor=sp.edit();

        edit.putString(id+"email",newemail);  //STORE THE USER INFO
        edit.putString(id+"name",newname);

        edit.apply();



        String details=sp.getString(newemail+newpass,"Email or Password is incorrect");   //IF THE ID AND PASSWORD IS CORRECT, WELCOME THE USER
        editor.putString("id",details);                                                      //STORE THE USER INFO TO KEY welcome, AND START THE GREETING ACTIVITY
        editor.apply();




        Intent signedup=new Intent(Signup.this,UserDetails.class);
        startActivity(signedup);



    }
}
