package fr.epsi.epsig2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class StudentsOnlineActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_online)
        showBtnBack()
        setHeaderTitle(getString(R.string.txt_home_student_online))
        val students = arrayListOf<Student>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val studentAdapter = StudentAdapter(this,students)
        recyclerView.adapter = studentAdapter


        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL ="https://djemam.com/epsi/list.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if(data !=null){
                    val jsOb= JSONObject(data)
                    val jsArray =jsOb.getJSONArray("items")
                    for(i in 0 until jsArray.length()){
                        val jsStudent = jsArray.getJSONObject(i)
                        val name =jsStudent.optString("name","")
                        val email =jsStudent.optString("email","")
                        val picture_url =jsStudent.optString("picture_url","")
                        val city =jsStudent.optString("city","Paris")
                        val phone =jsStudent.optString("phone","000000")
                        val zipcode =jsStudent.optString("zipcode","99999")
                        val student = Student(name, email = email, imgUrl = picture_url, phone = phone, zipcode = zipcode, city = city)
                        students.add(student)
                        Log.d("Student",student.name)
                    }
                    runOnUiThread(Runnable {
                        studentAdapter.notifyDataSetChanged()
                    })

//                    Handler(Looper.getMainLooper()).post(Runnable {
//                        studentAdapter.notifyDataSetChanged()
//                    })

                    Log.d("WS",data)
                    Log.d("Student","${students.size}")
                }
            }
        })


    }
}