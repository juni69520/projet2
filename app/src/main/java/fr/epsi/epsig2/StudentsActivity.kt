package fr.epsi.epsig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class StudentsActivity : BaseActivity() {

    val data = "{\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"picture_url\": \"https://www.numerama.com/content/uploads/2019/05/trou-noir-espace-univers-astronomie.jpg\",\n" +
            "      \"name\": \"Allan\",\n" +
            "      \"city\": \"bordeaux\",\n" +
            "      \"zipcode\": \"33000\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"email\": \"allan@epsi.fr\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"picture_url\": \"https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg\",\n" +
            "      \"name\": \"Arraud\",\n" +
            "      \"city\": \"bordeaux\",\n" +
            "      \"zipcode\": \"33000\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"email\": \"arraud@epsi.fr\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"picture_url\": \"https://media.gettyimages.com/photos/colorful-powder-explosion-in-all-directions-in-a-nice-composition-picture-id890147976?s=2048x2048\",\n" +
            "      \"name\": \"Nicolas\",\n" +
            "      \"city\": \"bordeaux\",\n" +
            "      \"zipcode\": \"33000\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"email\": \"nicolas@epsi.fr\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"picture_url\": \"https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg\",\n" +
            "      \"name\": \"Lilian\",\n" +
            "      \"city\": \"bordeaux\",\n" +
            "      \"zipcode\": \"33000\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"email\": \"lilian@epsi.fr\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"picture_url\": \"https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/2/3/1/2310c9171a_50157784_pia23441.jpg\",\n" +
            "      \"name\": \"Maxime\",\n" +
            "      \"city\": \"bordeaux\",\n" +
            "      \"zipcode\": \"33000\",\n" +
            "      \"phone\": \"0619191919\",\n" +
            "      \"email\": \"maxime@epsi.fr\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        val students = arrayListOf<Student>()
        val jsOb= JSONObject(data)
        val jsArray =jsOb.getJSONArray("items")
        for(i in 0 until jsArray.length()){
            val jsStudent = jsArray.getJSONObject(i)
            val name =jsStudent.optString("name","")
            val email =jsStudent.optString("email","")
            val picture_url =jsStudent.optString("picture_url","")
            val city =jsStudent.optString("city","")
            val phone =jsStudent.optString("phone","")
            val zipcode =jsStudent.optString("zipcode","")
            val student = Student(name, email = email, imgUrl = picture_url, phone = phone, zipcode = zipcode, city = city)
            students.add(student)
            Log.d("Student",student.name)
        }
        Log.d("Student","${students.size}")


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val studentAdapter = StudentAdapter(this,students)
        recyclerView.adapter = studentAdapter
    }
}