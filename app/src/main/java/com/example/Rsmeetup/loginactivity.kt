package com.example.Rsmeetup

import android.R
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Rsmeetup.signupActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    var emailBox: EditText? = null
    var passwordBox: EditText? = null
    var loginBtn: Button? = null
    var signupBtn: Button? = null
    var auth: FirebaseAuth? = null
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Please wait...")
        auth = FirebaseAuth.getInstance()
        emailBox = findViewById(R.id.emailBox)
        passwordBox = findViewById(R.id.passwordBox)
        loginBtn = findViewById(R.id.loginbtn)
        signupBtn = findViewById(R.id.createBtn)
        loginBtn.setOnClickListener(View.OnClickListener {
            dialog!!.show()
            val email: String
            val password: String
            email = emailBox.getText().toString()
            password = passwordBox.getText().toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult?>() {
                    fun onComplete(task: Task<AuthResult?>) {
                        dialog!!.dismiss()
                        if (task.isSuccessful()) {
                            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                task.getException().getLocalizedMessage(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
        })
        signupBtn.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    signupActivity::class.java
                )
            )
        })
    }
}