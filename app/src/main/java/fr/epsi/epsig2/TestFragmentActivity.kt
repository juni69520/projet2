package fr.epsi.epsig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TestFragmentActivity : BaseActivity() {

    val firstTabFragment=FirstTabFragment()
    val secondTabFragment=SecondTabFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        val textViewTab1 = findViewById<TextView>(R.id.textViewTab1)
        val textViewTab2 = findViewById<TextView>(R.id.textViewTab2)

        textViewTab1.setOnClickListener( View.OnClickListener {
            showTab1()
        })


        textViewTab2.setOnClickListener( View.OnClickListener {
            showTab2()
        })

        showTab1()
    }

    private fun showTab1() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, FirstTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTab2() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, MapsFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("sF") // name can be null
        transaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }

}