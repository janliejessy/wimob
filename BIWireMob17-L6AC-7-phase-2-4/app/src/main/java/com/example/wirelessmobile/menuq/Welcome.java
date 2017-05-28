package com.example.wirelessmobile.menuq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView img = new ImageView(this);  // or (ImageView) findViewById(R.id.myImageView);
        img.setImageResource(R.drawable.menu);
    }

    public void startButtonPressed(View view)
    {
        Intent intend = new Intent(Welcome.this, HomeActivity.class);
        startActivity(intend);
    }


}
