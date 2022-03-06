package fr.epsi.epsig2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SaveDataActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        editTextFirstName.setText(readSharedPreferences("firstName"))
        editTextLastName.setText(readSharedPreferences("lastName"))
        editTextEmailAddress.setText(readSharedPreferences("email"))

        buttonSave.setOnClickListener(View.OnClickListener {
            writeSharedPreferences("firstName",editTextFirstName.text.toString())
            writeSharedPreferences("lastName",editTextLastName.text.toString())
            writeSharedPreferences("email",editTextEmailAddress.text.toString())
        })
    }
}