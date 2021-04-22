package com.example.Rsmeetup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignupActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var emailBox: EditText? = null
    var passwordBox: EditText? = null
    var nameBox: EditText? = null
    var loginBtn: Button? = null
    var signupBtn: Button? = null
    var database: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        emailBox = findViewById(R.id.emailBox)
        nameBox = findViewById(R.id.namebox)
        passwordBox = findViewById(R.id.passwordBox)
        loginBtn = findViewById(R.id.loginbtn)
        signupBtn = findViewById(R.id.createBtn)
         signupBtn.setOnClickListener(View.OnClickListener {
            val email: String
            val pass: String
            val name: String
            email = emailBox.getText().toString()
            pass = passwordBox.getText().toString()
            name = nameBox.getText().toString()
            val user = User()
            user.email = email
            user.pass = pass
            user.name = name
            auth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    database!!.collection("Users")
                        .document().set(user).addOnSuccessListener {
                            startActivity(
                                Intent(
                                    this@SignupActivity,
                                    LoginActivity::class.java
                                )
                            )
                        }
                    //                            Toast.makeText(SignupActivity.this, "Account is created.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                        this@SignupActivity,
                        task.exception!!.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}