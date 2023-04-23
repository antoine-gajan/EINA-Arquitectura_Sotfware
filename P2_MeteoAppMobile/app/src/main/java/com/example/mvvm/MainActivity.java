package com.example.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;

public class MainActivity extends AppCompatActivity {
    Button create_user;
    Button create_meteo_station;
    Button link;
    Button update;
    Button show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.create_user = (Button)findViewById(R.id.create_user);
        this.create_meteo_station = (Button)findViewById(R.id.create_meteo_station);
        this.link = (Button)findViewById(R.id.link);
        this.update = (Button)findViewById(R.id.update);
        this.show = (Button) findViewById(R.id.show);

        create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_user_activity = new Intent(getApplicationContext(), CreateUserActivity.class);
                startActivity(create_user_activity);
                finish();
            }
        });

        create_meteo_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_meteo_station_activity = new Intent(getApplicationContext(), CreateMeteoStationActivity.class);
                startActivity(create_meteo_station_activity);
                finish();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if user or meteo station exists
                if (Model.get_instance().get_list_meteo_station().isEmpty() || Model.get_instance().get_list_users().isEmpty()){
                    // Display toast message
                    Toast.makeText(getApplicationContext(), "No user or meteo station found", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Start link activity
                    Intent link = new Intent(getApplicationContext(), LinkUserMeteoActivity.class);
                    startActivity(link);
                    finish();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If no meteo station
                if (Model.get_instance().get_list_meteo_station().isEmpty()){
                    // Display toast message
                    Toast.makeText(getApplicationContext(), "No user or meteo station found", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Start update activity
                    Intent update_station = new Intent(getApplicationContext(), UpdateMeteoInformationActivity.class);
                    startActivity(update_station);
                    finish();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If no meteo station
                if (Model.get_instance().get_list_users().isEmpty()){
                    // Display toast message
                    Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Start show user notification activity
                    Intent show_user = new Intent(getApplicationContext(), SelectUserForInformationActivity.class);
                    startActivity(show_user);
                    finish();
                }
            }
        });
    }



    // any change in toastMessage attribute
// defined on the Button with bind prefix
// invokes this method
    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}