package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

  private lateinit var mapView: MapView
  private lateinit var googleMap: GoogleMap

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_map, container, false)
    mapView = view.findViewById(R.id.map_view)
    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync(this)
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    mapView.onResume()
  }

  override fun onMapReady(map: GoogleMap) {
    googleMap = map
    // Customize and add markers, set camera position, etc.
    val location = LatLng(37.7749, -122.4194)
    googleMap.addMarker(MarkerOptions().position(location).title("Marker in San Francisco"))
    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}
