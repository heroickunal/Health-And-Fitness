package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import azzukunj.cottonuniversity.healthandfitnesskunjazzu.calorie.calorie;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.converttoless;
import azzukunj.cottonuniversity.healthandfitnesskunjazzu.converttoless.HoloCircularProgressBar2;
public class HR extends AppCompatActivity {
    protected boolean mAnimationHasEnded = false;




    private Button HeartRate;

    private HoloCircularProgressBar2 mHoloCircularProgressBar2;



    private ObjectAnimator mProgressBarAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);


        mHoloCircularProgressBar2 = (HoloCircularProgressBar2) findViewById(
                R.id.holoCircularProgressBar2);



        SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);



        SharedPreferences.Editor editor=sp.edit();
        String progressholo="180";
        editor.putString("progressholo",progressholo);
        editor.apply();


            mHoloCircularProgressBar2.setProgressColor(Color.RED);

            //randomColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            mHoloCircularProgressBar2.setProgressBackgroundColor(Color.WHITE);
            animate(mHoloCircularProgressBar2, null, 0, 3000);




        float putprogress=1.5f;
        animate(mHoloCircularProgressBar2, null, putprogress, 3000);
        mHoloCircularProgressBar2.setMarkerProgress(putprogress);
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
    } }