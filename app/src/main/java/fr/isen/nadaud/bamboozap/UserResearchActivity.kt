package fr.isen.nadaud.bamboozap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.isen.nadaud.bamboozap.databinding.ActivityUserResearchBinding

class UserResearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserResearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserResearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = arrayOf("Clémence","Enzo","Louise","Jules","Charles","Marc")

        val userAdapter : ArrayAdapter<String> = ArrayAdapter(
            this,android.R.layout.simple_list_item_1,
            user
        )

        binding.userList.adapter = userAdapter;

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if(user.contains(query)){
                    userAdapter.filter.filter(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }
        })

        binding.userList.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
