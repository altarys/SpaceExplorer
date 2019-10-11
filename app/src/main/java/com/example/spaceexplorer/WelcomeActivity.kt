package com.example.spaceexplorer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceexplorer.adapters.PlanetRecyclerViewAdapter
import com.example.spaceexplorer.models.Planet
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlin.random.Random

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val username = intent.getStringExtra("Username")
        txtvBonjour.text = resources.getString(R.string.msgBonjourExplorateur, username)

        rcvPlanetes.layoutManager = LinearLayoutManager(this)
        rcvPlanetes.adapter = PlanetRecyclerViewAdapter(generatePlanets())
        rcvPlanetes.adapter!!.notifyDataSetChanged()
    }

    private fun generatePlanets() : List<Planet>{
        val planets = mutableListOf<Planet>()
        val numberToGenerate = Random.nextInt(6,15)

        for(i in 0..numberToGenerate) {
            val newPlanet = Planet("Planete $i", "", Random.nextDouble())
            planets.add(newPlanet)
        }

        return planets.sortedWith(compareBy({it.distance}))
    }

    companion object {
        fun newIntent(context: Context, username: String) : Intent {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.putExtra("Username", username)
            return intent
        }
    }
}
