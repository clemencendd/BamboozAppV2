package fr.isen.nadaud.bamboozap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.isen.nadaud.bamboozap.databinding.ActivityRegisterBinding
import fr.isen.nadaud.bamboozap.model.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var reference: DatabaseReference
    private lateinit var rootNode: FirebaseDatabase
    private lateinit var mUser: FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    var cpt = 1
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerUserButton.setOnClickListener {
            mAuth = FirebaseAuth.getInstance()
            rootNode = FirebaseDatabase.getInstance()
            val userName = binding.userNameEditText.text.toString()
            val userLastName = binding.userLastNameEditText.text.toString()
            var userEmail = binding.userEmailEditText.text.toString()
            val userPassword = binding.userPasswordEditText.text.toString()
            var user = User(userEmail, userPassword, userName, userLastName, "")
            reference = rootNode.getReference("Users")

            if(userName.isNotEmpty() && userEmail.isNotEmpty() && userLastName.isNotEmpty() && userPassword.isNotEmpty()){
                if(userEmail.matches(emailPattern.toRegex()))
                {
                    if(userPassword.length < 6){
                        Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        createUserDatabase()
                        reference.child(userName).setValue(user).addOnCompleteListener { task ->
                            if(task.isSuccessful)
                            {
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                cpt++
                                goToFilActuActivity()
                            }
                            else {
                                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                } else {
                    Toast.makeText(this, "Email not conform", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun goToFilActuActivity() {
        val intent = Intent(this, FilActuActivity::class.java)
        startActivity(intent)
    }

    private fun createUserDatabase() {
        mAuth = FirebaseAuth.getInstance()
        val userEmail = binding.userEmailEditText.text.toString()
        val userPassword = binding.userPasswordEditText.text.toString()

        if(!userEmail.matches(emailPattern.toRegex())){
            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show()
        }
        else if (userPassword.isEmpty() || userPassword.length < 6){
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
        }
        else {
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}