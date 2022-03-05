package fr.epsi.epsig2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class StudentAdapter (val context: BaseActivity, private val students: ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewEmail = view.findViewById<TextView>(R.id.textViewEmail)
        val textViewPhone = view.findViewById<TextView>(R.id.textViewPhone)
        val textViewZipcode = view.findViewById<TextView>(R.id.textViewZipcode)
        val textViewCity = view.findViewById<TextView>(R.id.textViewCity)
        val imageViewStudent = view.findViewById<ImageView>(R.id.imageViewStudent)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_student, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students.get(position)
        holder.textViewName.text=student.name
        holder.textViewEmail.text=student.email
        holder.textViewPhone.text=student.phone
        holder.textViewCity.text=student.city
        holder.textViewZipcode.text=student.zipcode

        Picasso.get().load(student.imgUrl).into(holder.imageViewStudent)
        holder.contentLayout.setOnClickListener(View.OnClickListener {
            (holder.contentLayout.context.applicationContext as AppEpsi).showToast(student.name)

            val newIntent= Intent(context,DetailActivity::class.java)
            newIntent.putExtra("title",context.getString(R.string.txt_home_space))
            newIntent.putExtra("urlImage","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkkZFu63Vi0djeAwcj0d7aaEgBsS1eHpGLbw&usqp=CAU")
            context.startActivity(newIntent)
        })
    }

    override fun getItemCount(): Int {
        return students.size
    }
}