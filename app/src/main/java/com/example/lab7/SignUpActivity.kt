package com.example.lab7

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirmPassword: EditText
    private lateinit var btnCreateUser: Button
    private val CREDENTIAL_SHARED_PREF = "our_shared_pref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edUsername = findViewById(R.id.ed_username)
        edPassword = findViewById(R.id.ed_password)
        edConfirmPassword = findViewById(R.id.ed_confirm_pwd)
        btnCreateUser = findViewById(R.id.btn_create_user)

        btnCreateUser.setOnClickListener {
            val strPassword = edPassword.text.toString()
            val strConfirmPassword = edConfirmPassword.text.toString()
            val strUsername = edUsername.text.toString()

            when {
                strUsername.isEmpty() -> {
                    Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
                }
                strPassword.isEmpty() -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                }
                strConfirmPassword.isEmpty() -> {
                    Toast.makeText(this, "Please confirm password", Toast.LENGTH_SHORT).show()
                }
                strPassword.length < 6 -> {
                    Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                }
                strPassword != strConfirmPassword -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE)
                    val editor = credentials.edit()
                    editor.putString("Password", strPassword)
                    editor.putString("Username", strUsername)
                    editor.commit()

                    Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
} 