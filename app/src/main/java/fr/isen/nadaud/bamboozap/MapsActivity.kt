package fr.isen.nadaud.bamboozap

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.nadaud.bamboozap.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setNewMarket(mMap)

    }

    private fun setNewMarket(map: GoogleMap){

        val database = Firebase.database("https://bamboozapp-de010-default-rtdb.firebaseio.com/").getReference("challenge")

        database.get().addOnSuccessListener {

            for (ds in it.children) {

                Toast.makeText(this,"La base de données a été lu", Toast.LENGTH_SHORT).show()

                if (it.exists()) {

                    val latitude = ds.child("latitude").getValue(Double::class.java) as Double
                    val longitude = ds.child("longitude").getValue(Double::class.java) as Double
                    val description = ds.child("description").getValue(String::class.java)

                    val cities = LatLng(latitude, longitude)
                    val zoomLevel = 5f // f : float number

                    map.addMarker(MarkerOptions().position(cities).title(description))
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(cities, zoomLevel))

                }
            }
        }
    }
}