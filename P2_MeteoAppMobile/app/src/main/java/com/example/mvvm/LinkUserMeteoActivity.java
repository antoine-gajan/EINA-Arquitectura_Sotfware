package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class LinkUserMeteoActivity extends AppCompatActivity {
    LinearLayout layout;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_user_meteo);
        this.layout = (LinearLayout) findViewById(R.id.link_user_meteo);

        // Title
        TextView title = new TextView(this);
        title.setText("Choose a user");
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        this.layout.addView(title);

        // For each user
        for (Usuario u : Model.get_instance().get_list_users()){
            Button button_user = new Button(this);
            button_user.setText(String.format("%s %s", u.getNombre(), u.getApellido()));
            this.layout.addView(button_user);

            // On click event
            button_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Start new activity
                    Intent choose_station_activity = new Intent(getApplicationContext(), ChooseMeteoStationActivity.class);
                    choose_station_activity.putExtra("user", Model.get_instance().get_list_users().indexOf(u));
                    startActivity(choose_station_activity);
                    finish();
                }
            });
        }
    }
}