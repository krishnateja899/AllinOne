package com.example.allinone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button login, register;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.email_phoneLogin);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.register_btn);
        queue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0) {
                    username.setError("Please enter the username");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("Please enter password");
                } else if (username.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
                    username.setError("Please enter the username");
                    password.setError("Please enter password");
                } else {
                    logIn();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void logIn() {

        String URL = "http://10.0.2.2/LoginRegister/login.php";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            private static final String TAG = "TAG";

            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onResponse: " + response);
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("username", username.getText().toString());
                hashMap.put("password", password.getText().toString());
                return hashMap;
            }
        };
        queue.add(request);
    }
}