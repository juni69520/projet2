package fr.epsi.epsig2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BarcodeTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BarcodeTabFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_barcode_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editLastName = view.findViewById<TextView>(R.id.textViewLastName)
        val editFirstName = view.findViewById<TextView>(R.id.textViewNickName)

        editLastName.text = readSharedPreferences("lastName")
        editFirstName.text = readSharedPreferences("firstName")
        displayBitmap(readSharedPreferences("barcode").toString())
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
         * @return A new instance of fragment BarcodeTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarcodeTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun displayBitmap(value: String) {
        val widthPixels = resources.getDimensionPixelSize(R.dimen.width_barcode)
        val heightPixels = resources.getDimensionPixelSize(R.dimen.height_barcode)
        val imageBarcode = view?.findViewById<ImageView>(R.id.image_barcode)
        val textBarcodeNumber = view?.findViewById<TextView>(R.id.textViewBarcode)

        imageBarcode?.setImageBitmap(
            createBarcodeBitmap(
                barcodeValue = value,
                barcodeColor = ResourcesCompat.getColor(resources,R.color.black,null),
                backgroundColor = ResourcesCompat.getColor(resources,R.color.white,null),
                widthPixels = widthPixels,
                heightPixels = heightPixels
            )
        )
        if (textBarcodeNumber != null) {
            textBarcodeNumber.text = value
        }
    }

    private fun createBarcodeBitmap(
        barcodeValue: String,
        @ColorInt barcodeColor: Int,
        @ColorInt backgroundColor: Int,
        widthPixels: Int,
        heightPixels: Int
    ): Bitmap {
        val bitMatrix = Code128Writer().encode(
            barcodeValue,
            BarcodeFormat.CODE_128,
            widthPixels,
            heightPixels
        )

        val pixels = IntArray(bitMatrix.width * bitMatrix.height)
        for (y in 0 until bitMatrix.height) {
            val offset = y * bitMatrix.width
            for (x in 0 until bitMatrix.width) {
                pixels[offset + x] =
                    if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
            }
        }

        val bitmap = Bitmap.createBitmap(
            bitMatrix.width,
            bitMatrix.height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.setPixels(
            pixels,
            0,
            bitMatrix.width,
            0,
            0,
            bitMatrix.width,
            bitMatrix.height
        )
        return bitmap
    }
}