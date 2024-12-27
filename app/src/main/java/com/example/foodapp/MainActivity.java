package com.example.foodapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etemail;
    EditText etpassword;
    Button signIn;

    TextView forgotpassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etemail = findViewById(R.id.et_email);
        etpassword = findViewById(R.id.et_password);
        signIn = findViewById(R.id.btn_logIn);
        forgotpassword = findViewById(R.id.forgot_password);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


        etemail.addTextChangedListener(textWatcher);
        etpassword.addTextChangedListener(textWatcher);


        signIn.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            String email = etemail.getText().toString();
            String password = etpassword.getText().toString();

            if (email.equals("admin") && password.equals("admin")) {
                Snackbar.make(v, "Вы успешно зарегистрировались", Snackbar.LENGTH_LONG).show();
                etemail.setVisibility(View.GONE);
                etpassword.setVisibility(View.GONE);
                signIn.setVisibility(View.GONE);
                forgotpassword.setVisibility(View.GONE);
            } else {
                Snackbar.make(v, "Неверный логин или пароль", Snackbar.LENGTH_LONG).show();
            }

        });
    }
}

