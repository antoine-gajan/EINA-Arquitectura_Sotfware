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

public class ShowUserInfoActivity extends AppCompatActivity {

    LinearLayout layout;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);

        this.layout = (LinearLayout) findViewById(R.id.layout_show_user_info);

        // Get user
        Bundle b = getIntent().getExtras();
        int id = b.getInt("user");
        this.user = Model.get_instance().get_list_users().get(id);

        // Title
        TextView title = new TextView(this);
        title.setText(String.format("Last notification of %s %s", this.user.getNombre(), this.user.getApellido()));
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        this.layout.addView(title);

        if (user.get_info() == null){
            // No information to show
            TextView temperature = new TextView(this);
            temperature.setText(String.format("%s %s hasn't received notification yet !", user.getNombre(), user.getApellido()));
            this.layout.addView(temperature);
        }
        else {
            // Display relevant information
            switch (user.get_aspect()) {
                case "Temperature":
                    TextView temperature = new TextView(this);
                    temperature.setText(String.format("Temperature : %f", user.get_info().get_temperature()));
                    this.layout.addView(temperature);
                    break;
                case "Wind":
                    TextView wind = new TextView(this);
                    wind.setText(String.format("Wind : %f", user.get_info().get_wind()));
                    this.layout.addView(wind);
                    break;
                case "Rain":
                    TextView precipitation = new TextView(this);
                    precipitation.setText(String.format("Rain : %f", user.get_info().get_rain()));
                    this.layout.addView(precipitation);
            }
        }

        // Submit button
        Button submit = new Button(this);
        submit.setText("Go back to main menu");
        this.layout.addView(submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to main activity
                Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_activity);
                finish();
            }
        });
    }
}