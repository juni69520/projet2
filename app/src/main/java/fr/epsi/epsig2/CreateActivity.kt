package fr.epsi.epsig2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val buttonSave = findViewById<Button>(R.id.buttonLogin)
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        val editTextZipcode = findViewById<EditText>(R.id.editTextZipcode)
        val editTextBarcode = findViewById<EditText>(R.id.editTextBarcode)

        if(readSharedPreferences("firstName") != "Not found"){
            editTextFirstName.setText(readSharedPreferences("firstName"))
            editTextLastName.setText(readSharedPreferences("lastName"))
            editTextEmailAddress.setText(readSharedPreferences("email"))
            editTextAddress.setText(readSharedPreferences("address"))
            editTextCity.setText(readSharedPreferences("city"))
            editTextZipcode.setText(readSharedPreferences("zipcode"))
            editTextBarcode.setText(readSharedPreferences("barcode"))
        }

        buttonSave.setOnClickListener(View.OnClickListener {
            writeSharedPreferences("firstName",editTextFirstName.text.toString())
            writeSharedPreferences("lastName",editTextLastName.text.toString())
            writeSharedPreferences("email",editTextEmailAddress.text.toString())
            writeSharedPreferences("address",editTextAddress.text.toString())
            writeSharedPreferences("city",editTextCity.text.toString())
            writeSharedPreferences("zipcode",editTextZipcode.text.toString())
            writeSharedPreferences("barcode",editTextBarcode.text.toString())

            val newIntent= Intent(application,FragmentActivity::class.java)
            startActivity(newIntent)
        })
    }
}