package com.sinichi.parentingcontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sinichi.parentingcontrol.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView tvLogin;
    private TextInputEditText tiedtPasswordReg;
    private TextInputEditText tiedtConfirmPasswordReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Authenticate user input
                boolean isValidPasswordAndConfirm = validatePassAndConfirm(tiedtPasswordReg, tiedtConfirmPasswordReg);
                boolean is8CharacterPassword = min8CharacterOfPassword(tiedtConfirmPasswordReg);
                if (isValidPasswordAndConfirm) {
                    if (is8CharacterPassword) {
                        Toast.makeText(RegisterActivity.this, "Register berhasil. Anda bisa login sesuai kredensial.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        tiedtPasswordReg.setError("Password minimal 8 karakter");
                    }
                } else {
                    tiedtConfirmPasswordReg.setError("Password tidak cocok");
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Authenticate user input

                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void initComponents() {
        tvLogin = findViewById(R.id. tv_sudahPunyaAkun);
        btnRegister = findViewById(R.id.btn_login);
        tiedtPasswordReg = findViewById(R.id.tiedt_passwordReg);
        tiedtConfirmPasswordReg = findViewById(R.id.tiedt_confirmPasswordReg);
    }

    private boolean validatePassAndConfirm(TextInputEditText editTextPassword, TextInputEditText editTextConfirmPassword) {
        String password = editTextPassword.getText().toString();
        String confPassword = editTextConfirmPassword.getText().toString();

        return TextUtils.equals(password, confPassword);
    }

    private boolean min8CharacterOfPassword(TextInputEditText edtTextPassword) {
        char[] password = edtTextPassword.getText().toString().toCharArray();
        return (password.length >= 8);
    }
}
