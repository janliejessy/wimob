package com.example.wirelessmobile.menuq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void appetizersButtonPressed(View view)
    {
        Intent intend = new Intent(this, Appetizers.class);
        startActivity(intend);
    }

    public void mainCourseButtonPressed(View view)
    {
        Intent intend = new Intent(this, Appetizers.class);
        startActivity(intend);
    }

    public void dessertsButtonPressed(View view)
    {
        Intent intend = new Intent(this, Appetizers.class);
        startActivity(intend);
    }

    public void drinksButtonPressed(View view)
    {
        Intent intend = new Intent(this, Appetizers.class);
        startActivity(intend);
    }
}
