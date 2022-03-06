package fr.epsi.epsig2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    private var qrScan: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showBtnBack()
        setHeaderTitle("Login")

        //Initialize the Scan Object
        qrScan = IntentIntegrator(this)

        //OnClickListener Attached to the Button
        qrScan!!.initiateScan()

    }

    //Getting the scan results
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            //Check to see if QR Code has nothing in it
            if (result.contents == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                //QR Code contains some data
                try {
                    //Convert the QR Code Data to JSON
                    val obj = JSONObject(result.contents)
                    //Set up the TextView Values using the data from JSON
                    writeSharedPreferences("firstName", obj.getString("firstName"))
                    writeSharedPreferences("lastName", obj.getString("lastName"))
                    writeSharedPreferences("email", obj.getString("email"))
                    writeSharedPreferences("address", obj.getString("address"))
                    writeSharedPreferences("city", obj.getString("city"))
                    writeSharedPreferences("zipcode", obj.getString("zipcode"))
                    writeSharedPreferences("barcode", obj.getString("cardRef"))

                    val newIntent= Intent(application,CreateActivity::class.java)
                    startActivity(newIntent)

                } catch (e: JSONException) {
                    e.printStackTrace()
                    //In case of exception, display whatever data is available on the QR Code
                    //This can be caused due to the format MisMatch of the JSON
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}