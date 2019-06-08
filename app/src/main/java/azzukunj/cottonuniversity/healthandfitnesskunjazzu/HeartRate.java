package azzukunj.cottonuniversity.healthandfitnesskunjazzu;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.support.v7.app.ActionBar;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.HoloCircularProgressBar2;

public class HeartRate extends AppCompatActivity {

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;
    private String clientid = "";
    private Timer myTimer;
    Button subscribe;
    private HoloCircularProgressBar2 mHoloCircularProgressBar2;



    private ObjectAnimator mProgressBarAnimator;

    TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        subscribe=findViewById(R.id.subscribe);

        mHoloCircularProgressBar2 = (HoloCircularProgressBar2) findViewById(
                R.id.holoCircularProgressBar2);
        tvMessage = (TextView) findViewById(R.id.subscribedMsg);




        pahoMqttClient = new PahoMqttClient();

        String urlBroker = "tcp://postman.cloudmqtt.com:17099";
        clientid="kunj";
        String username  = "eqvdnaau";
        String password  = "akZj_MfMgyab";
        client = pahoMqttClient.getMqttClient(  getApplicationContext(),
                urlBroker,
                clientid,
                username,
                password
        );

        mqttCallback();


        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScheduleTasks();
            }

        }, 0, 1000);



        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pahoMqttClient.mqttAndroidClient.isConnected() ) {
                    String msg_new = "Currently not connected to MQTT broker: Must be connected to subscribe to a topic\r\n";
                    tvMessage.append(msg_new);

                }
                String topic = "esp/test";
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.subscribe(client, topic, 1);
                        String msg_new = "Added subscription topic: " + "" + "\r\n";
                        tvMessage.append(msg_new);

                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        mHoloCircularProgressBar2.setProgressColor(Color.RED);

        //randomColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        mHoloCircularProgressBar2.setProgressBackgroundColor(Color.WHITE);
        animate(mHoloCircularProgressBar2, null, 0, 3000);





    }

    private void animate(final HoloCircularProgressBar2 progressBar,
                         final Animator.AnimatorListener listener) {
        final float progress = (float) (Math.random() * 2);
        int duration = 3000;
        animate(progressBar, listener, progress, duration);
    }
    private void animate(final HoloCircularProgressBar2 progressBar, final Animator.AnimatorListener listener,
                         final float progress, final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
        mProgressBarAnimator.setDuration(duration);

        mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                progressBar.setProgress(progress);
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            }

            @Override
            public void onAnimationStart(final Animator animation) {
            }
        });
        if (listener != null) {
            mProgressBarAnimator.addListener(listener);
        }
        mProgressBarAnimator.reverse();
        mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                progressBar.setProgress((Float) animation.getAnimatedValue());
            }
        });
        progressBar.setMarkerProgress(progress);
        mProgressBarAnimator.start();
    }
    private void ScheduleTasks()
    {

        this.runOnUiThread(RunScheduledTasks);
    }


    private Runnable RunScheduledTasks = new Runnable() {
        public void run() {

            TextView tvMessage  = (TextView) findViewById(R.id.subscribe);
            String msg_new="";

            if(pahoMqttClient.mqttAndroidClient.isConnected() ) {
                msg_new = "Connected\r\n";
                tvMessage.setTextColor(0xFF00FF00); //Green if connected
                tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            else {
                msg_new = "Disconnected\r\n";
                tvMessage.setTextColor(0xFFFF0000); //Red if not connected
                tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            tvMessage.setText(msg_new);
        }
    };


    protected void mqttCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                tvMessage = (TextView) findViewById(R.id.subscribedMsg);
                if(topic.equals("mycustomtopic1")) {
                }
                else if(topic.equals("mycustomtopic2")) {
                }
                else {
                    String msg = "topic: " + topic + "\r\nMessage: " + message.toString() + "\r\n";
                    tvMessage.setText( msg);
                    String bar=message.toString();
                    if(bar.equals("65"))
                    {
                    Random random=new Random();
                    int r=random.nextInt(75-60+1)+60;
                    float f=(float)r/100;

                    animate(mHoloCircularProgressBar2, null, f, 3000);
                    mHoloCircularProgressBar2.setMarkerProgress(f);
                }}
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}
