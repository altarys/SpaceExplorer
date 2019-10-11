package com.example.spaceexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Remove title bar
        setContentView(R.layout.activity_main)
    }

    fun btnConnexionClick(view: View?) {
        if(edtMotDePasse.text.toString() == "123456"){
            val intent = WelcomeActivity.newIntent(this, edtNomUtilisateur.text.toString())
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.msgMauvaisMotDePasse, Toast.LENGTH_LONG).show()
        }
    }
}
