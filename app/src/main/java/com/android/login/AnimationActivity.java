package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvListener;
    private ImageView ivElement;
    private Button btnBlink, btnRotate, btnFade, btnMove, btnSlide, btnZoom,btnBounce, btnSequential, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initViews();
        setListerners();
    }

    private void initViews() {
        ivElement = findViewById(R.id.ivElement);
        btnBlink = findViewById(R.id.btnBlink);
        btnRotate = findViewById(R.id.btnRotate);
        btnFade = findViewById(R.id.btnFade);
        btnMove = findViewById(R.id.btnMove);
        btnSlide = findViewById(R.id.btnSlide);
        btnZoom = findViewById(R.id.btnZoom);
        btnStop = findViewById(R.id.btnStop);
        btnBounce = findViewById(R.id.btnBounce);
        btnSequential = findViewById(R.id.btnSequence);
        tvListener = findViewById(R.id.tvListener);
    }

    private void setListerners(){
        btnBlink.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnFade.setOnClickListener(this);
        btnMove.setOnClickListener(this);
        btnSlide.setOnClickListener(this);
        btnZoom.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnBounce.setOnClickListener(this);
        btnSequential.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBlink:
                Animation animation_blink = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
                ivElement.startAnimation(animation_blink);
                animation_blink.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        tvListener.setText("On animation start");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvListener.setText("On animation end");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        tvListener.setText("Repeating animation");
                    }
                });
                break;
            case R.id.btnRotate:
                Animation animation_rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
                ivElement.startAnimation(animation_rotate);
                break;
            case R.id.btnFade:
                Animation animation_fade = AnimationUtils.loadAnimation(this, R.anim.fade_animation);
                ivElement.startAnimation(animation_fade);
                break;
            case R.id.btnMove:
                Animation animation_move = AnimationUtils.loadAnimation(this, R.anim.move_animation);
                ivElement.startAnimation(animation_move);
                break;
            case R.id.btnSlide:
                Animation animation_slide = AnimationUtils.loadAnimation(this, R.anim.slide_animation);
                ivElement.startAnimation(animation_slide);
                break;
            case R.id.btnZoom:
                ivElement.animate().setDuration(1000).scaleX(1).scaleY(1).scaleXBy(2).scaleYBy(2).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        tvListener.setText("On animation Start");
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        tvListener.setText("On animation End");
                        ivElement.setScaleX(1);
                        ivElement.setScaleY(1);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        tvListener.setText("On animation Cancel");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                        tvListener.setText("On animation Repeat");
                    }
                });

//                Animation animation_zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_animation);
//                ivElement.startAnimation(animation_zoom);
                break;
            case R.id.btnBounce:
                Animation animation_bounce = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
                ivElement.startAnimation(animation_bounce);
                break;
            case R.id.btnSequence:
                 Animation animation_sequence = AnimationUtils.loadAnimation(this, R.anim.sequential_animation);
                 ivElement.startAnimation(animation_sequence);
                 break;
            case R.id.btnStop:
                ivElement.clearAnimation();
                break;
        }
    }
}
