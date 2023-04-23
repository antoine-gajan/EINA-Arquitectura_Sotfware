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

public class SelectUserForInformationActivity extends AppCompatActivity {

    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_for_information);

        this.layout = (LinearLayout) findViewById(R.id.layout_select_user_for_info);

        // Title
        TextView title = new TextView(this);
        title.setText("Select user");
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
                    Intent show_user_info = new Intent(getApplicationContext(), ShowUserInfoActivity.class);
                    show_user_info.putExtra("user", Model.get_instance().get_list_users().indexOf(u));
                    startActivity(show_user_info);
                    finish();
                }
            });
        }
    }


}