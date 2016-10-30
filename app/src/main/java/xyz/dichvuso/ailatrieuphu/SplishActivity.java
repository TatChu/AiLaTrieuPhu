package xyz.dichvuso.ailatrieuphu;

/**
 * Created by sword on 11/04/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplishActivity extends Activity{
    ImageView mImageView;
    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);

        mImageView = (ImageView) findViewById(R.id.imgMoney);

        initAnim();
        setListener();
    }


    private void initAnim() {
        this.mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        this.mFadeIn.setDuration(500);
        this.mFadeInScale = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
        this.mFadeInScale.setDuration(4000);
        this.mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        this.mFadeOut.setDuration(500);
        this.mImageView.startAnimation(this.mFadeIn);
    }

    public void setListener() {
        this.mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(SplishActivity.this.mFadeInScale);
            }
        });
        this.mFadeInScale.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                SplishActivity.this.finish();
            }
        });
        this.mFadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
            }
        });
    }
}