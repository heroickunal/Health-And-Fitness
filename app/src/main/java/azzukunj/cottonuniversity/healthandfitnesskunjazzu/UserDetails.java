package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class UserDetails extends AppCompatActivity{
    private Button start;
    public EditText agex,weightx,heightx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        agex=findViewById(R.id.age);
        weightx=findViewById(R.id.weight);
        heightx=findViewById(R.id.height);




    }
    public void start(View v)
    {
        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String age=agex.getText().toString();
        String weight=weightx.getText().toString();
        String height=heightx.getText().toString();
        SharedPreferences.Editor edit=sp.edit();
        String id=sp.getString("id","Email or Password is incorrect");
        edit.putString(id+"age",age);//STORE THE USER INFO
        edit.putString(id+"weight",weight);
        edit.putString(id+"height",height);

        edit.apply();
        Intent signedup=new Intent(UserDetails.this,MainActivity.class);
        startActivity(signedup);

    }



}
