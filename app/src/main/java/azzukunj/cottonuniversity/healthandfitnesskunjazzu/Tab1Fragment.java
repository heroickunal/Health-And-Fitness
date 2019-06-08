package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


import azzukunj.cottonuniversity.healthandfitnesskunjazzu.holocircularprogressbar.HoloCircularProgressBar;

import static android.content.Context.MODE_PRIVATE;

public class Tab1Fragment extends Fragment {
    protected boolean mAnimationHasEnded = false;

    private Switch mAutoAnimateSwitch;


    private Button mColorSwitchButton;

    private HoloCircularProgressBar mHoloCircularProgressBar;

    private Button mOne;

    private ObjectAnimator mProgressBarAnimator;

    private Button mZero;


    private TextView textView;
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST,btnTEST2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity().getApplication(),sitUps.class);
                startActivity(i);
            }
        });
        btnTEST2= (Button) view.findViewById(R.id.pushups);

        btnTEST2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity().getApplication(),pushUps.class);
                startActivity(i);
            }
        });


        textView=view.findViewById(R.id.total);
        mHoloCircularProgressBar = (HoloCircularProgressBar) view.findViewById(
                R.id.holoCircularProgressBar);

        {

            mHoloCircularProgressBar.setProgressColor(Color.GREEN);

            //randomColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            mHoloCircularProgressBar.setProgressBackgroundColor(Color.WHITE);

        }

        SharedPreferences sp=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);

        String tc=sp.getString("r","0");
        String tx=sp.getString("totalcal","0");
        //totalcal.setText(tc);

        int x=Integer.parseInt(tc);
        int y=Integer.parseInt(tx);

        int total = x + y;

        textView.setText(Integer.toString(total));

        if (mProgressBarAnimator != null) {
            mProgressBarAnimator.cancel();
        }
        float progress=total;



        if (mProgressBarAnimator != null) {
            mProgressBarAnimator.cancel();
        }
        float j=total;
        progress=j/500;
        animate(mHoloCircularProgressBar, null, progress, 2000);
        mHoloCircularProgressBar.setMarkerProgress(progress);

        return view;

    }
    public void Button2(View v)
    {

    }


    private void animate(final HoloCircularProgressBar progressBar,
                         final Animator.AnimatorListener listener) {
        final float progress = (float) (Math.random() * 2);
        int duration = 3000;
        animate(progressBar, listener, progress, duration);
    }

    private void animate(final HoloCircularProgressBar progressBar, final Animator.AnimatorListener listener,
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
}