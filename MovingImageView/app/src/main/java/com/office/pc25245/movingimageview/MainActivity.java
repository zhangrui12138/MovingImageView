package com.office.pc25245.movingimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.office.pc25245.movingimageview.com.john.view.MovingImageView;

public class MainActivity extends AppCompatActivity {
   private MovingImageView movingImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        movingImageView = (MovingImageView) findViewById(R.id.moving);
    }
}
