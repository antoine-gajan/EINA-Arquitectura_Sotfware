package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CreateMeteoStationActivity extends AppCompatActivity {
    Button submit;
    EditText city;
    EditText temperature;
    EditText wind;
    EditText precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meteo_station);

        this.submit = (Button) findViewById(R.id.submit_station);
        this.temperature = (EditText) findViewById(R.id.temperature_station);
        this.wind = (EditText) findViewById(R.id.wind_station);
        this.precipitation = (EditText) findViewById(R.id.precipitation_station);
        this.city = (EditText) findViewById(R.id.city_station);

        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create meteo station
                CadenaMeteo cadena = new CadenaMeteo(city.getText().toString(), Double.parseDouble(temperature.getText().toString()), Double.parseDouble(precipitation.getText().toString()), Double.parseDouble(wind.getText().toString()));
                // Add meteo station
                Model.get_instance().add_meteo_station(cadena);
                // Display toast message
                Toast.makeText(getApplicationContext(), "Meteo station successfully created !", Toast.LENGTH_SHORT).show();
                Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_activity);
                finish();
            }
        });
    }
}