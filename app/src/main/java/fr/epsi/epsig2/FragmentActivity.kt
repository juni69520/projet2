package fr.epsi.epsig2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_fragment)

        val textViewTab1 = findViewById<TextView>(R.id.textViewTab1)
        val textViewTab2 = findViewById<TextView>(R.id.textViewTab2)
        val textViewTab3 = findViewById<TextView>(R.id.textViewTab3)
        val imageViewProfile = findViewById<ImageView>(R.id.imageViewProfile)

        setProfileLogo()

        textViewTab1.setOnClickListener( View.OnClickListener {
            showBarcodeTab()
        })


        textViewTab2.setOnClickListener( View.OnClickListener {
            showTab2()
        })

        textViewTab3.setOnClickListener( View.OnClickListener {
            showMapsFragmentTab()
        })

        imageViewProfile.setOnClickListener( View.OnClickListener {
            showProfileTab()
        })

        showBarcodeTab()
    }

    private fun showBarcodeTab() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, BarcodeTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTab2() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, OffersTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("sF") // name can be null
        transaction.commit()
    }

    private fun showMapsFragmentTab() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, MapsFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("aA") // name can be null
        transaction.commit()
    }

    private fun showProfileTab() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, ProfileTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("aB") // name can be null
        transaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }
}