package fr.isen.nadaud.bamboozap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import fr.isen.nadaud.bamboozap.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference=database.getReference("Users")

        binding.loginUserButton2.setOnClickListener {
            mAuth = Firebase.auth
            var userEmail = binding.userEmailEditText3.text.toString()
            var userPassword = binding.userPasswordEditText2.text.toString()

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this, "email or password must not be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        goToFilActuActivity()
                    } else {
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun goToFilActuActivity() {
        val intent = Intent(this, FilActuActivity::class.java)
        startActivity(intent)
    }

}