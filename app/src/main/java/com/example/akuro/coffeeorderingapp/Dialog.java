package com.example.akuro.coffeeorderingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dialog extends AppCompatDialogFragment {

    private EditText mNameEditText;
    private DialogListener listener;
    private Button mProceedButton;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_alert_dialog, null);

        builder.setView(view);

        mProceedButton = view.findViewById(R.id.proceedButton);
        mProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(mNameEditText.getText().length() == 0)) {
                    String username = mNameEditText.getText().toString();
                    listener.getTexts(username);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Enter your name to proceed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNameEditText = view.findViewById(R.id.editTextName);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DialogListener) context;
    }

    public interface DialogListener {
        void getTexts(String username);
    }
}
