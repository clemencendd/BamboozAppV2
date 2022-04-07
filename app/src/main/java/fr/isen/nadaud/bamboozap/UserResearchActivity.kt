package fr.isen.nadaud.bamboozap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.isen.nadaud.bamboozap.databinding.ActivityUserResearchBinding

class UserResearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserResearchBinding
    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserResearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users")

        listView = findViewById(R.id.userList)

    }

}
