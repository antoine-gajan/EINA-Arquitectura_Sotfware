package com.example.mvvm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {

    Button submit;
    EditText nombre;
    EditText apellido;
    EditText age;
    RadioButton rain;
    RadioButton temperature;
    RadioButton wind;
    RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        this.submit = (Button)findViewById(R.id.submit_create_user);
        this.nombre = (EditText) findViewById(R.id.user_name);
        this.apellido = (EditText) findViewById(R.id.user_surname);
        this.radio_group = (RadioGroup) findViewById(R.id.user_radio_group);
        this.age = (EditText) findViewById(R.id.user_age);
        this.rain = (RadioButton) findViewById(R.id.user_rain);
        this.wind = (RadioButton) findViewById(R.id.user_wind);
        this.temperature = (RadioButton) findViewById(R.id.user_temperature);

        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the radio button checked
                int selected = radio_group.getCheckedRadioButtonId();
                RadioButton btn_selected = (RadioButton) findViewById(selected);
                // Create user
                Usuario user = new Usuario(nombre.getText().toString(), apellido.getText().toString(), Integer.parseInt(age.getText().toString()), btn_selected.getText().toString());
                // Add user
                Model.get_instance().add_user(user);
                // Display toast message
                Toast.makeText(getApplicationContext(), "User successfully created !", Toast.LENGTH_SHORT).show();
                Intent main_activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_activity);
                finish();
            }
        });
    }
}