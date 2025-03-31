package com.example.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private val CREDENTIAL_SHARED_PREF = "our_shared_pref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edUsername = findViewById(R.id.ed_username)
        edPassword = findViewById(R.id.ed_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val usernameFromEd = edUsername.text.toString()
            val passwordFromEd = edPassword.text.toString()

            when {
                usernameFromEd.isEmpty() -> {
                    Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
                }
                passwordFromEd.isEmpty() -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE)
                    val strUsername = credentials.getString("Username", null)
                    val strPassword = credentials.getString("Password", null)

                    if (strUsername == null || strPassword == null) {
                        Toast.makeText(this, "No user found. Please sign up", Toast.LENGTH_SHORT).show()
                    } else if (!strUsername.equals(usernameFromEd, ignoreCase = true)) {
                        Toast.makeText(this, "Username is incorrect", Toast.LENGTH_SHORT).show()
                    } else if (!strPassword.equals(passwordFromEd, ignoreCase = true)) {
                        Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}