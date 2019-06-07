package azzukunj.cottonuniversity.healthandfitnesskunjazzu;


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


public class HeartRate extends AppCompatActivity {

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;
    private String clientid = "";
    private Timer myTimer;
    Button subscribe;


    TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        subscribe=findViewById(R.id.subscribe);


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
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}
