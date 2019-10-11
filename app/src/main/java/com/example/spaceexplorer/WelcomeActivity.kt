package com.example.spaceexplorer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceexplorer.adapters.PlanetRecyclerViewAdapter
import com.example.spaceexplorer.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlin.random.Random

class WelcomeActivity : AppCompatActivity() {

    private var planets = listOf<Planet>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val username = intent.getStringExtra("Username")
        txtvBonjour.text = resources.getString(R.string.msgBonjourExplorateur, username)

        rcvPlanetes.layoutManager = LinearLayoutManager(this)

        loadPlanets()
    }

    private fun loadPlanets() {
        Services.PLANET_API_URL.httpGet().responseJson { request, response, result ->

            when(result) {
                is Result.Success -> {
                    planets = Json.nonstrict.parse(Planet.serializer().list, result.value.content)
                    rcvPlanetes.adapter = PlanetRecyclerViewAdapter(planets.sortedWith(compareBy({it.distance})))
                    rcvPlanetes.adapter!!.notifyDataSetChanged()
                    txtvNbPlanetes.text = resources.getPluralZeroString(R.plurals.msgNearPlanet, R.string.msgNearPlanetZero, planets.size)
                }
                is Result.Failure -> {
                    toast(result.toString())
                }
            }
        }
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
