package com.example.allinone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.allinone.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Youtube Video: https://www.youtube.com/watch?v=X8oD4q3XtQQ
 * Github: https://github.com/VishnuSivadasVS/LogIn-SignUp
 * Go to HelperFiles/LoginFiles and read the txt file on how to integrate the php files and test the login.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText fullName, email, userName, password;
    private Button register;
    private TextView login;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.register_fullName);
        email = findViewById(R.id.register_email);
        userName = findViewById(R.id.register_userName);
        password = findViewById(R.id.register_password);
        register = findViewById(R.id.register_btn1);
        login = findViewById(R.id.register_login);
        queue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().length() == 0 || email.getText().toString().length() == 0 || userName.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
                    fullName.setError("Enter your Name");
                    email.setError("Enter your email");
                    userName.setError("Enter username");
                    password.setError("Enter a password");

                } else if (fullName.getText().toString().length() == 0) {
                    fullName.setError("Enter your Name");
                } else if (email.getText().toString().length() == 0) {
                    email.setError("Enter your email");
                } else if (userName.getText().toString().length() == 0) {
                    userName.setError("Enter username");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("Enter a password");
                } else {
                    Registeruser();
                }
            }
        });
    }

    private void Registeruser() {
        String URL = "http://10.0.2.2/LoginRegister/signup.php";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            private static final String TAG = "TAG";

            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onResponse: " + response);
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    fullName.setText("");
                    email.setText("");
                    userName.setText("");
                    password.setText("");
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("fullname", fullName.getText().toString());
                hashMap.put("email", email.getText().toString());
                hashMap.put("username", userName.getText().toString());
                hashMap.put("password", password.getText().toString());
                return hashMap;
            }
        };
        queue.add(request);
    }
}