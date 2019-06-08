package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;

import static android.content.Context.MODE_PRIVATE;

public class DetailsOfUser extends Fragment {

    private TextView name,email,age,weight,height;
    private Button reset,logout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details_of_user,container,false);

        name=view.findViewById(R.id.logname);
        email=view.findViewById(R.id.logemail);
        age=view.findViewById(R.id.logage);
        height=view.findViewById(R.id.logheight);
        weight=view.findViewById(R.id.logweight);
        reset=view.findViewById(R.id.resetdetails);
        logout=view.findViewById(R.id.logout);



        SharedPreferences sp=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);
        String id=sp.getString("id","Email or Password is incorrect");
        String sname=sp.getString(id+"name","Email or Password is incorrect");
        String semail=sp.getString(id+"email","Email or Password is incorrect");
        String sage=sp.getString(id+"age","Email or Password is incorrect");
        String sweight=sp.getString(id+"weight","Email or Password is incorrect");
        String sheight=sp.getString(id+"height","Email or Password is incorrect");
        name.setText(sname);
        email.setText(semail);
        age.setText(sage);
        weight.setText(sweight);
        height.setText(sheight);
reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(getActivity().getApplication(),Signup.class);
        startActivity(i);
    }
});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),MainActivity.class);
                startActivity(i);
            }
        });
  return view;
    }
}
