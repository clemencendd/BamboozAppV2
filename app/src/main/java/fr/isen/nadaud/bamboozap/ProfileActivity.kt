package fr.isen.nadaud.bamboozap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.nadaud.bamboozap.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UserProfile()

    }

    private fun UserProfile(){

        val database = Firebase.database("https://bamboozapp-de010-default-rtdb.firebaseio.com/").getReference("Users")

        database.get().addOnSuccessListener {

            for (ds in it.children) {

                Toast.makeText(this,"La base de données a été lu", Toast.LENGTH_SHORT).show()

                if (it.exists()) {

                    val name = ds.child("name").getValue(String::class.java)
                    val lastName = ds.child("lastName").getValue(String::class.java)

                    binding.nameData.text = lastName

                }
            }
        }
    }
}