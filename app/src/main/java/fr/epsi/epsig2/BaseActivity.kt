package fr.epsi.epsig2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showBtnBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setProfileLogo(){
        val profile = findViewById<ImageView>(R.id.imageViewProfile)
        profile.visibility = View.VISIBLE
        profile.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,ProfileTabFragment::class.java)
            startActivity(newIntent)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Epsi","################ onCreate ##############"+javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.d("Epsi","################ onResume ##############"+javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("Epsi","################ onPause ##############"+javaClass.simpleName)
    }

    fun showToast(txt : String){
        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()
    }

    fun readSharedPreferences(key : String) : String{
        val sharedPreferences = getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val txt = sharedPreferences.getString(key, "Not found")
        return txt.toString()
    }

    fun writeSharedPreferences(key : String , value : String){
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }
}