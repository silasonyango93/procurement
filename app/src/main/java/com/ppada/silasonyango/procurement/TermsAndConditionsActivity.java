package com.ppada.silasonyango.procurement;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TermsAndConditionsActivity extends Activity {
    ImageView img;
    TextView tvTopic1,tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        tvTopic1= findViewById(R.id.tvContent1);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "JosefinSans-Light.ttf");

        // tvConservation.setTypeface(custom_font);
        tvTopic1.setTypeface(custom_font);    }
}
