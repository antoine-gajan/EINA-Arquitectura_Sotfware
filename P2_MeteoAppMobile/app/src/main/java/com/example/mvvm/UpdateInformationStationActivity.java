package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateInformationStationActivity extends AppCompatActivity {

    LinearLayout layout;
    CadenaMeteo cadena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information_station);

        this.layout = (LinearLayout) findViewById(R.id.layout_update_info_station);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("station");
        this.cadena = Model.get_instance().get_list_meteo_station().get(id);

        // Title
        TextView title = new EditText(this);
        title.setText(String.format("%s", this.cadena.get_state().get_city()));
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        this.layout.addView(title);

        // EditText for all information
        EditText temperature = new EditText(this);
        temperature.setInputType(InputType.TYPE_CLASS_NUMBER);
        temperature.setText("Temperature");
        this.layout.addView(temperature);

        EditText wind = new EditText(this);
        wind.setInputType(InputType.TYPE_CLASS_NUMBER);
        wind.setText("Wind");
        this.layout.addView(wind);

        EditText precipitation = new EditText(this);
        precipitation.setInputType(InputType.TYPE_CLASS_NUMBER);
        precipitation.setText("Precipitation");
        this.layout.addView(precipitation);

        // Submit button
        Button submit = new Button(this);
        submit.setText("Update information");
        this.layout.addView(submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update information if possible
                boolean success = true;
                double temp = 0;
                double win = 0;
                double prec = 0;
                String city = cadena.get_state().get_city();
                try {
                    temp = Double.parseDouble(temperature.getText().toString());
                    win = Double.parseDouble(wind.getText().toString());
                    prec = Double.parseDouble(precipitation.getText().toString());
                } catch (NumberFormatException e) {
                    // Error type
                    // Display toast message
                    success = false;
                    Toast.makeText(getApplicationContext(), "Impossible to update : value error !", Toast.LENGTH_SHORT).show();
                }

                // If update possible
                if (success) {
                    // Do the update
                    MeteoInformation new_info = new MeteoInformation(city, temp, win, prec);
                    cadena.set_state(new_info);

                    // Toast message
                    Toast.makeText(getApplicationContext(), String.format("Meteo information of %s successfully updated", city), Toast.LENGTH_SHORT).show();

                    // Return to main activity
                    Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main_activity);
                    finish();
                }
            }
        });
    }
}