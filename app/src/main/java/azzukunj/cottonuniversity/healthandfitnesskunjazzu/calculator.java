package azzukunj.cottonuniversity.healthandfitnesskunjazzu;





import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import g1.g1;
import situps.sp;
import gc2.gc2;
import pushups.pu;
import calculator.c;



public class calculator extends AppCompatActivity {


            g1 GU=new g1(12,3);
            g1 GU2=new g1(12,3);
            g1 GU3=new g1(12,3);
            g1 GU4=new g1(12,3);
            g1 GU5=new g1(12,3);
            g1 GU6=new g1(12,3);
            g1 GU7=new g1(12,3);
            g1 GU8=new g1(12,3);
            g1 GU9=new g1(12,3);
            g1 GU10=new g1(12,3);
            g1 GU11=new g1(12,3);
            g1 GU12=new g1(12,3);
            g1 GU13=new g1(12,3);
            g1 GU14=new g1(12,3);
            g1 GU15=new g1(12,3);
            g1 GU16=new g1(12,3);
            g1 GU17=new g1(12,3);
            g1 GU18=new g1(12,3);
    gc2 GcU=new gc2(12);
    gc2 GcU2=new gc2(12);
    gc2 GcU3=new gc2(12);
    gc2 GcU4=new gc2(13);
    gc2 GcU5=new gc2(1);
    sp gc1=new sp();
    sp gc2=new sp();
    sp gc3=new sp();
    sp gc4=new sp();
    sp gc5=new sp();
    pu p1=new pu();
    pu p2=new pu();
    pu p3=new pu();
    pu p4=new pu();
    pu p5=new pu();
    c c1=new c();
    c c2=new c();
    c c3=new c();
    c c4=new c();
    c c5=new c();




    Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
                    button7 , button8 , button9 , buttonAdd , buttonSub , buttonDivision ,
                    buttonMul , button10 , buttonC , buttonEqual,buttongc ;
            String gg="Garbage collector run successfully";

            EditText edt1 ;

            float mValueOne , mValueTwo ;

            boolean mAddition , mSubtract ,mMultiplication ,mDivision ;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_calculator);


                button0 = (Button) findViewById(R.id.button0);
                button1 = (Button) findViewById(R.id.button1);
                button2 = (Button) findViewById(R.id.button2);
                button3 = (Button) findViewById(R.id.button3);
                button4 = (Button) findViewById(R.id.button4);
                button5 = (Button) findViewById(R.id.button5);
                button6 = (Button) findViewById(R.id.button6);
                button7 = (Button) findViewById(R.id.button7);
                button8 = (Button) findViewById(R.id.button8);
                button9 = (Button) findViewById(R.id.button9);
                button10 = (Button) findViewById(R.id.button10);
                buttonAdd = (Button) findViewById(R.id.buttonadd);
                buttonSub = (Button) findViewById(R.id.buttonsub);
                buttonMul = (Button) findViewById(R.id.buttonmul);
                buttongc = (Button) findViewById(R.id.gc);
                buttonDivision = (Button) findViewById(R.id.gc);
                buttonC = (Button) findViewById(R.id.buttonC);
                buttonEqual = (Button) findViewById(R.id.buttoneql);
                edt1 = (EditText) findViewById(R.id.edt1);


                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"1");
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"2");
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"3");
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"4");
                    }
                });

                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"5");
                    }
                });

                button6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"6");
                    }
                });

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"7");
                    }
                });

                button8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"8");
                    }
                });

                button9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"9");
                    }
                });

                button0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+"0");
                    }
                });

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (edt1 == null){
                            edt1.setText("");
                        }else {
                            mValueOne = Float.parseFloat(edt1.getText() + "");
                            mAddition = true;
                            edt1.setText(null);
                        }
                    }
                });

                buttonSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        mSubtract = true ;
                        edt1.setText(null);
                    }
                });

                buttonMul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        mMultiplication = true ;
                        edt1.setText(null);
                    }
                });

                buttonDivision.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mValueOne = Float.parseFloat(edt1.getText()+"");
                        mDivision = true ;
                        edt1.setText(null);
                    }
                });

                buttonEqual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mValueTwo = Float.parseFloat(edt1.getText() + "");
                        g1 GU=new g1(12,3);
                        g1 GU2=new g1(12,3);
                        g1 GU3=new g1(12,3);
                        g1 GU4=new g1(12,3);
                        g1 GU5=new g1(12,3);
                        g1 GU6=new g1(12,3);
                        g1 GU7=new g1(12,3);
                        g1 GU8=new g1(12,3);
                        g1 GU9=new g1(12,3);
                        g1 GU10=new g1(12,3);
                        g1 GU11=new g1(12,3);
                        g1 GU12=new g1(12,3);
                        g1 GU13=new g1(12,3);
                        g1 GU14=new g1(12,3);
                        g1 GU15=new g1(12,3);
                        g1 GU16=new g1(12,3);
                        g1 GU17=new g1(12,3);
                        g1 GU18=new g1(12,3);


                        if (mAddition == true){

                            edt1.setText(mValueOne + mValueTwo +"");
                            mAddition=false;
                        }


                        if (mSubtract == true){
                            edt1.setText(mValueOne - mValueTwo+"");
                            mSubtract=false;
                        }

                        if (mMultiplication == true){
                            edt1.setText(mValueOne * mValueTwo+"");
                            mMultiplication=false;
                        }

                        if (mDivision == true){
                            edt1.setText(mValueOne / mValueTwo+"");
                            mDivision=false;
                        }
                    }
                });

                buttonC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //edt1.setText("");
                        GU=null;
                        GU2=null;
                        GU3=null;
                        GU4=null;
                        GU5=null;
                        GU6=null;
                        GU7=null;
                        GU8=null;
                        GU9=null;
                        GU10=null;
                        GU11=null;
                        GU12=null;
                        GU13=null;
                        GU14=null;
                        GU15=null;
                        GU16=null;
                        GU17=null;
                        GU18=null;
                        GcU2=null;
                        GcU=null;
                        GcU3=null;
                        GcU4=null;
                        GcU5=null;
                        gc1=null;
                        gc2=null;
                        gc3=null;
                        gc4=null;
                        gc5=null;
                        p1=null;
                        p2=null;
                        p3=null;
                        p4=null;
                        p5=null;
                        c1=null;
                        c2=null;
                        c3=null;
                        c4=null;
                        c5=null;


                        System.gc();

                        Toast.makeText(getApplicationContext(), "GC", Toast.LENGTH_SHORT).show();
                    }
                });

                button10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(edt1.getText()+".");
                    }
                });
                buttongc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt1.setText(gg);

                    }
                });
            }



        }


