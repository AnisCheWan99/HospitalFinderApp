package com.example.signindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class About extends AppCompatActivity implements View.OnClickListener {

    ImageView github;
    ImageView youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        github = findViewById(R.id.github);

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                gotoUrl("https://github.com/AnisCheWan99/BMICalculatorApp"); }
        });

        youtube = findViewById(R.id.youtube);

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                gotoUrl("https://www.youtube.com/watch?v=CuklIb9d3fI"); }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public void onClick(View v) {

    }




    //call menu_nav (menu bar) dari main activity

    public void openProfile(){

        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.profile:
                startActivity(new Intent(About.this,About.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}