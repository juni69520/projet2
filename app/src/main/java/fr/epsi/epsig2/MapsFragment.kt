package fr.epsi.epsig2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MapsFragment : Fragment(){
    private val mMarkerMap: MutableMap<Marker, Store> = hashMapOf()
    lateinit var googleMap :GoogleMap
    @SuppressLint("MissingPermission", "UseRequireInsteadOfGet")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled=true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled=true
                // Only approximate location access granted.
            } else -> {
            // No location access granted.
        }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL="https://djemam.com/epsi/stores.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {

                val stores = arrayListOf<Store>()
                val data = response.body?.string()
                val jsCities= JSONObject(data)

                val items=jsCities.getJSONArray("stores")
                for (i in 0 until items.length()){
                    val city = items.getJSONObject(i)
                    var cityLatLng = LatLng(city.optDouble("latitude",0.0),city.optDouble("longitude",0.0))

                    if(city.optString("city") == "Pau"){
                       cityLatLng = LatLng(city.optDouble("longitude",0.0),city.optDouble("latitude",0.0))
                    }else if(city.optString("city") == "Nantes"){
                        cityLatLng = LatLng(47.218371, -1.553621)
                    }

                    runOnUiThread {
                        val fullAddress =  city.optString("address","") + " - " + city.optString("zipcode","") + " " + city.optString("city","");
                        val marker = googleMap.addMarker(MarkerOptions().position(cityLatLng).title(city.optString("name","")).snippet(fullAddress));
                        val storeName = city.optString("name");
                        val storeAddress = city.optString("address");
                        val storeZipcode =  city.optString("zipcode");
                        val storeCity = city.optString("city");
                        val storeImg = city.optString("pictureStore");
                        val storeDesc = city.optString("description");

                        val markerStore = Store(storeName, storeAddress, storeZipcode, storeCity, storeImg, storeDesc)
                        stores.add(markerStore)

                        if (marker != null) {
                            mMarkerMap[marker] = markerStore
                        };
                    }
                }
            }
        })

        val paris = LatLng(48.854885, 2.338646)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(paris))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris,5.5f))

        googleMap.setOnMapClickListener {
            (activity as BaseActivity).showToast(it.toString())
        }

        googleMap.setOnInfoWindowClickListener { p0 ->
                activity?.let {
                    val intent = Intent(it, StoreActivity::class.java)
                    val markerInfo: Store? = mMarkerMap[p0]
                    if (markerInfo != null) {
                        intent.putExtra("storeName", markerInfo.storeName)
                    };
                    if (markerInfo != null) {
                        intent.putExtra("storeAddress", markerInfo.storeAddress)
                    };
                    if (markerInfo != null) {
                        intent.putExtra("storeZipCodeCity", markerInfo.storeZipcode+" - "+markerInfo.storeCity)
                    };
                    if (markerInfo != null) {
                        intent.putExtra("storeImg", markerInfo.storeImg)
                    };
                    if (markerInfo != null) {
                        intent.putExtra("storeDesc", markerInfo.storeDesc)
                    };
                    startActivity(intent);
                    it.startActivity(intent)
                }
        }
        this.googleMap=googleMap
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }
}