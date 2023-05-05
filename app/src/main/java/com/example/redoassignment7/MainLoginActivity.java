package com.example.redoassignment7;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.HashMap;
import android.util.Log;

public class MainLoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private HashMap<String, User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        usernameInput = findViewById(R.id.userName);
        passwordInput = findViewById(R.id.passWord);
        loginButton = findViewById(R.id.loginButton);

        createSampleUsers();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (validateUser(username, password)) {
                    launchApp(getCurrentUser(username));
                } else {
                    Toast.makeText(MainLoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void createSampleUsers() {
        users = new HashMap<>();
        Account checkingAccount1 = new Account("1001", 1000000.00);
        Account savingsAccount1 = new Account("2001", 20000.00);
        User user1 = new User("randy_savage", "12345", checkingAccount1, savingsAccount1);

        Account checkingAccount2 = new Account("1002", 300000000.00);
        Account savingsAccount2 = new Account("2002", 400000000.00);
        User user2 = new User("elon_musk", "54321", checkingAccount2, savingsAccount2);

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }


    private boolean validateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return true;
        }
        return false;
    }

    private User getCurrentUser(String username) {
        return users.get(username);
    }


    private void launchApp(User user) {
        if (user != null) {
            Log.d("MainLoginActivity", "User is not null");
        } else {
            Log.e("MainLoginActivity", "User is null");
        }
        Intent i = new Intent(MainLoginActivity.this, MainActivity.class);
        i.putExtra("user", (Parcelable) user);
        startActivity(i);
    }
}
