package fr.isen.nadaud.bamboozap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.nadaud.bamboozap.databinding.ActivityFilActuBinding

class FilActuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilActuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilActuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {

                R.id.localisationBtn -> {

                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.photoBtn -> {

                    val intent = Intent(this, PublishActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.searchBtn -> {

                    val intent = Intent(this, UserResearchActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.profileBtn -> {

                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false

            }
        }
    }

}