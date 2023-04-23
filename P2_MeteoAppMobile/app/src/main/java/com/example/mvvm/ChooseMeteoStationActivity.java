package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseMeteoStationActivity extends AppCompatActivity {
    Usuario user;
    LinearLayout layout;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meteo_station);

        this.layout = (LinearLayout) findViewById(R.id.layout_choose_meteo);

        // Title
        TextView title = new TextView(this);
        title.setText("Choose a meteo station");
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);

        // Get user
        Bundle b = getIntent().getExtras();
        int id = b.getInt("user");
        this.user = Model.get_instance().get_list_users().get(id);
        this.layout.addView(title);

        // For each meteo station
        for (CadenaMeteo c : Model.get_instance().get_list_meteo_station()){
            Button button_cadena = new Button(this);
            button_cadena.setText(String.format("%s", c.get_state().get_city()));
            this.layout.addView(button_cadena);

            // On click event
            button_cadena.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create link
                    c.attach(user);
                    // Show toast message
                    Toast.makeText(getApplicationContext(), "Link successfully created !", Toast.LENGTH_SHORT).show();
                    // Return to main
                    Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main_activity);
                    finish();
                }
            });
        }
    }
}