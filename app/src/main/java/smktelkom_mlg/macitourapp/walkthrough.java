package smktelkom_mlg.macitourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import smktelkom_mlg.macitourapp.adapter.SliderAdapter;

public class Walkthrough extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private Button mBack,mNext,mFinish;
    private int mCurrentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0) {
                mNext.setEnabled(true);
                mBack.setEnabled(false);
                mBack.setVisibility(View.INVISIBLE);
                mNext.setText("Next");
                mBack.setText("");
            } else if (position == mDots.length - 1) {
                mNext.setEnabled(false);
                mBack.setEnabled(true);
                mFinish.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.INVISIBLE);
                mFinish.setVisibility(View.VISIBLE);
                mBack.setText("Back");
            } else {
                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mFinish.setEnabled(false);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.VISIBLE);
                mFinish.setVisibility(View.INVISIBLE);
                mNext.setText("Next");
                mBack.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mBack =(Button) findViewById(R.id.prevBtn);
        mNext =(Button) findViewById(R.id.btnNext);
        mFinish =(Button) findViewById(R.id.btnFinish);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage +1);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage -1);
            }
        });
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Walkthrough.this, Login.class);
                startActivity(i);
                finish();
            }
        });


    }

    public void addDotsIndicator(int pos){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length >0 ){
            mDots[pos].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onClick(View v) {

    }
}
