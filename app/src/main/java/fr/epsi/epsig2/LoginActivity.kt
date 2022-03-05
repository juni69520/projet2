package fr.epsi.epsig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showBtnBack()
        setHeaderTitle("Login")

        val editTextEmailAddress= findViewById<EditText>(R.id.editTextEmailAddress)
        val editTextPassword= findViewById<EditText>(R.id.editTextPassword)

        val buttonLogin =findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener(View.OnClickListener {
            (application as AppEpsi).showToast(editTextEmailAddress.text.toString()+"/"+editTextPassword.text)
        })
    }
}