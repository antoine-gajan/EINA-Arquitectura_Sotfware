package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

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

public class UpdateMeteoInformationActivity extends AppCompatActivity {

    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meteo_information);

        this.layout = (LinearLayout) findViewById(R.id.layout_update_meteo);

        // Title
        TextView title = new EditText(this);
        title.setText("Choose a meteo station");
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
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
                    Intent update_info = new Intent(getApplicationContext(), UpdateInformationStationActivity.class);
                    update_info.putExtra("station", Model.get_instance().get_list_meteo_station().indexOf(c));
                    startActivity(update_info);
                    finish();
                }
            });
        }
    }
}