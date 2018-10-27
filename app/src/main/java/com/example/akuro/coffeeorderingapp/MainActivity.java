package com.example.akuro.coffeeorderingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener {

    public static final String mCoffeeTypeSpinnerId = "mSpinnerId";
    public static final String mCupSpinnerId = "mCupSpinnerId";

    //Basic Coffee ordering App
    Spinner mCoffeeTypeSpinner;
    Spinner mCupSpinner;
    Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("bool", true);

        if (firstStart) {
            //checking if it's the first start in the device.
            showAlertDialog();
        }


        mCoffeeTypeSpinner = findViewById(R.id.spinnerView);
        mCupSpinner = findViewById(R.id.cupSpinner);
        mNextButton = findViewById(R.id.nextButton);

        setupSpinners();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Confirmation_activity.class);

                if (mCupSpinner.getSelectedItem().equals("No. of cups")) {
                    Toast.makeText(MainActivity.this, "Choose No. of Cups before proceeding",
                            Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(mCupSpinnerId, mCupSpinner.getSelectedItem().toString());
                    intent.putExtra(mCoffeeTypeSpinnerId, mCoffeeTypeSpinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });

    }

    public void setupSpinners() {
        ArrayAdapter<String> mStringArray = new ArrayAdapter<String>(MainActivity.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.coffee_names));
        mStringArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCoffeeTypeSpinner.setAdapter(mStringArray);

        ArrayAdapter<String> mNoArray = new ArrayAdapter<String>(MainActivity.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.no_of_cups));
        mNoArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCupSpinner.setAdapter(mNoArray);

    }

    public void showAlertDialog() {
        Dialog dialog = new Dialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "showAlertDialog");
    }

    @Override
    public void getTexts(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("bool", false);
        editor.putString("username", username);
        editor.apply();
    }
}