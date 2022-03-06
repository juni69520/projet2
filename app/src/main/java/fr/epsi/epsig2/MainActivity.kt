package fr.epsi.epsig2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString("firstName","Not found")
        if (firstName != null && firstName != "Not found") {
          showToast(firstName)
            //val newIntent= Intent(application,TestFragmentActivity::class.java)
          //startActivity(newIntent)
        }else{
            showToast("rien")
        }

        val buttonLogin:Button = findViewById(R.id.buttonQR)
        val buttonCreate:Button = findViewById(R.id.buttonCreate)
        val buttonFragment:Button = findViewById(R.id.buttonFragment)


        buttonLogin.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,LoginActivity::class.java)
            startActivity(newIntent)
        })

        buttonCreate.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,CreateActivity::class.java)
            startActivity(newIntent)
        })

        buttonFragment.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,TestFragmentActivity::class.java)
            startActivity(newIntent)
        })
    }


}