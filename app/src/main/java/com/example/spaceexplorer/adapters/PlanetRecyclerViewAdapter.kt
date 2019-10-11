package com.example.spaceexplorer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceexplorer.R
import com.example.spaceexplorer.models.Planet
import com.example.spaceexplorer.toast
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_planet.view.*

class PlanetRecyclerViewAdapter (private val values: List<Planet>) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(holder){
            view.tag = item
            view.setOnClickListener {
                val planet = it.tag as Planet
                it.context.toast(planet.name)
            }
            bind(item)
        }

    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val txvNom: TextView = view.txvNom!!
        private val txvDistance : TextView = view.txvDistance!!
        private val imvPlanet : ImageView = view.imvPlanete!!

        fun bind(planet: Planet) {
            txvNom.text = planet.name
            txvDistance.text = planet.distance.toString() + " UA"

            Picasso.get().load(planet.type).fit().centerInside().into(imvPlanet)
            //Ion.with(imvPlanet.context).load(planet.type).withBitmap().intoImageView(imvPlanet)
        }
    }
}