package fr.epsi.epsig2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class StoreActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        val imageView= findViewById<ImageView>(R.id.imageView)
        val textViewStoreName = findViewById<TextView>(R.id.textViewStoreName)
        val textViewAddress = findViewById<TextView>(R.id.textViewAddress)
        val textViewZipCodeCity = findViewById<TextView>(R.id.textViewZipCodeCity)
        val textViewDesc = findViewById<TextView>(R.id.textViewDesc)
        val imageViewStore= findViewById<ImageView>(R.id.imageViewStore)

        showBtnBack()

        imageView.visibility=View.GONE;
        textViewStoreName.visibility=View.VISIBLE;

        intent.getStringExtra("storeName")?.let {
            textViewStoreName.text = it
        }

        intent.getStringExtra("storeAddress")?.let {
            textViewAddress.text = it
        }

        intent.getStringExtra("storeZipCodeCity")?.let {
            textViewZipCodeCity.text = it
        }

        intent.getStringExtra("storeDesc")?.let {
            textViewDesc.text = it
        }

        intent.getStringExtra("storeImg")?.let {
            Picasso.get().load(it).into(imageViewStore)
        }
    }
}