package com.example.dclassicsbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Show / Hide Password
        etPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (etPassword.getCompoundDrawables()[2] != null &&
                        event.getRawX() >= (etPassword.getRight()
                                - etPassword.getCompoundDrawables()[2].getBounds().width())) {

                    if (isPasswordVisible) {
                        // Sembunyikan password
                        etPassword.setInputType(
                                InputType.TYPE_CLASS_TEXT |
                                        InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.hide_password, 0);

                        isPasswordVisible = false;
                    } else {
                        // Tampilkan password
                        etPassword.setInputType(
                                InputType.TYPE_CLASS_TEXT |
                                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.show_password, 0);

                        isPasswordVisible = true;
                    }

                    // Supaya cursor tetap di akhir
                    etPassword.setSelection(etPassword.getText().length());

                    return true;
                }
            }
            return false;
        });

        btnLogin.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError("Username must be filled");
            etUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password must be filled");
            etPassword.requestFocus();
            return;
        }

        if (!password.matches("[a-zA-Z0-9]+")) {
            etPassword.setError("Password must be alphanumeric");
            etPassword.requestFocus();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USERNAME", username);
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}