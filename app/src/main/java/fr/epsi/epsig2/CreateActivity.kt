package fr.epsi.epsig2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        showBtnBack()
        setHeaderTitle("Create")

        val buttonSave = findViewById<Button>(R.id.buttonLogin)
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        val editTextZipcode = findViewById<EditText>(R.id.editTextZipcode)
        val editTextBarcode = findViewById<EditText>(R.id.editTextBarcode)


        buttonSave.setOnClickListener(View.OnClickListener {
            writeSharedPreferences("firstName",editTextFirstName.text.toString())
            writeSharedPreferences("lastName",editTextLastName.text.toString())
            writeSharedPreferences("email",editTextEmailAddress.text.toString())
            writeSharedPreferences("address",editTextAddress.text.toString())
            writeSharedPreferences("city",editTextCity.text.toString())
            writeSharedPreferences("zipcode",editTextZipcode.text.toString())
            writeSharedPreferences("barcode",editTextBarcode.text.toString())
        })
    }

    fun writeSharedPreferences(key : String , value : String){
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }


}