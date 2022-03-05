package fr.epsi.epsig2

import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        showBtnBack()
        intent.getStringExtra("title")?.let { setHeaderTitle(it) }
        val imageViewDetail = findViewById<ImageView>(R.id.imageViewDetail)
        val urlImage= intent.getStringExtra("urlImage")
        Picasso.get().load(urlImage).into(imageViewDetail)
    }
}