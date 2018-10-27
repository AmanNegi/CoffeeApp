package com.example.akuro.coffeeorderingapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation_activity extends AppCompatActivity {

    TextView mtvUserName, mtvNoOfCups, mTvTotalCost, mTypeOfCoffee, mTvRate;
    Button mPlaceOrderButton;

    String userName;
    String coffeeType;
    String cupsNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        coffeeType = getIntent().getStringExtra(MainActivity.mCoffeeTypeSpinnerId);
        cupsNo = getIntent().getStringExtra(MainActivity.mCupSpinnerId);

        mPlaceOrderButton = findViewById(R.id.placeOrderButton);
        mtvNoOfCups = findViewById(R.id.tvNoOfCups);
        mTvTotalCost = findViewById(R.id.tvTotalCost);
        mTvRate = findViewById(R.id.tvRate);
        mtvUserName = findViewById(R.id.tvUserName);
        mTypeOfCoffee = findViewById(R.id.tvCoffeeType);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "No Data Found");

        mtvUserName.setText(userName);
        mTypeOfCoffee.setText(coffeeType);
        mtvNoOfCups.setText(cupsNo);
        mTvRate.setText("$" + String.valueOf(getRate(coffeeType)));
        mTvTotalCost.setText("$" + String.valueOf(returnAmountToPay(cupsNo, coffeeType)));

        mPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialog();
            }
        });
    }

    public int getRate(String CoffeeType) {
        int cost = 0;
        switch (CoffeeType) {
            case "Americano":
                cost = 5;
                break;
            case "Cappuccino":
                cost = 4;
                break;
            case "Latte":
                cost = 3;
                break;
            default:
                //Mocha
                cost = 2;
                break;
        }
        return cost;
    }

    public Integer returnAmountToPay(String count, String CoffeeType) {
        int rate = getRate(CoffeeType);
        int _count = Integer.parseInt(count);
        return rate * _count;
    }

    public void createAlertDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.order_placed_alert, null);
        Button okButton = view.findViewById(R.id.okButton);

        new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create().show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
