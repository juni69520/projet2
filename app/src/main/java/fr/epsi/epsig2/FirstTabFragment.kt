package fr.epsi.epsig2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat.getColor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextFirstName = view.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = view.findViewById<EditText>(R.id.editTextLastName)
        val editTextEmailAddress = view.findViewById<EditText>(R.id.editTextEmailAddress)
        val editTextAddress = view.findViewById<EditText>(R.id.editTextAddress)
        val editTextCity = view.findViewById<EditText>(R.id.editTextCity)
        val editTextZipcode = view.findViewById<EditText>(R.id.editTextZipcode)
        val editTextBarcode = view.findViewById<EditText>(R.id.editTextBarcode)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        editTextFirstName.setText(readSharedPreferences("firstName"))
        editTextLastName.setText(readSharedPreferences("lastName"))
        editTextEmailAddress.setText(readSharedPreferences("email"))
        editTextAddress.setText(readSharedPreferences("address"))
        editTextCity.setText(readSharedPreferences("city"))
        editTextZipcode.setText(readSharedPreferences("zipcode"))
        editTextBarcode.setText(readSharedPreferences("barcode"))

        //displayBitmap("123456789")

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
        activity?.let{
            val sharedPreferences= it.getSharedPreferences("epsi", Context.MODE_PRIVATE)
            val edit=sharedPreferences.edit()
            edit.putString(key,value)
            edit.apply()
        }
    }

    fun readSharedPreferences(key : String) : String{
        activity?.let {
            val sharedPreferences = it.getSharedPreferences("epsi", Context.MODE_PRIVATE)
            val txt = sharedPreferences.getString(key, "Not found")
            return txt.toString()
        }
        return "Not found"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //private fun createBarcodeBitmap(
    //   barcodeValue: String,
    //    @ColorInt barcodeColor: Int,
    //    @ColorInt backgroundColor: Int,
    //    widthPixels: Int,
    //    heightPixels: Int
    //): Bitmap {
    //   val bitMatrix = Code128Writer().encode(
    //       barcodeValue,
    //       BarcodeFormat.CODE_128,
    //       widthPixels,
    //       heightPixels
    //    )
//
    //  val pixels = IntArray(bitMatrix.width * bitMatrix.height)
    //  for (y in 0 until bitMatrix.height) {
    //     val offset = y * bitMatrix.width
    //      for (x in 0 until bitMatrix.width) {
    //         pixels[offset + x] =
    //              if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
    //      }
//   }

    //  val bitmap = Bitmap.createBitmap(
    //     bitMatrix.width,
    //      bitMatrix.height,
    //     Bitmap.Config.ARGB_8888
    // )
    // bitmap.setPixels(
    //     pixels,
    //   0,
    //     bitMatrix.width,
    //   0,
    //   0,
    //   bitMatrix.width,
    //   bitMatrix.height
    //  )
    //  return bitmap
    //}

    //private fun displayBitmap(value: String) {
    //   val widthPixels = resources.getDimensionPixelSize(R.dimen.width_barcode)
    //   val heightPixels = resources.getDimensionPixelSize(R.dimen.height_barcode)
    //   val image_barcode = view?.findViewById<EditText>(R.id.image_barcode)
    //   val text_barcode_number = view?.findViewById<EditText>(R.id.text_barcode_number)
//
    //  image_barcode.setImageBitmap(
    //       createBarcodeBitmap(
    //        barcodeValue = value,
    //         barcodeColor = getColor(R.color.black),
    //        backgroundColor = getColor(android.R.color.white),
    //         widthPixels = widthPixels,
    //        heightPixels = heightPixels
    //    )
    //   )
    //  text_barcode_number?.text = value
    // }
}