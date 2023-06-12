package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pt.ulusofona.deisi.cm2223.g22202497_22000492.FusedLocation
import pt.ulusofona.deisi.cm2223.g22202497_22000492.OnLocationChangedListener
import pt.ulusofona.deisi.cm2223.g22202497_22000492.controller.NavigationManager
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MarkerData
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import java.util.*

class MapFragment : Fragment(), OnLocationChangedListener {

  private lateinit var binding: FragmentMapBinding
  private lateinit var mapView: MapView
  private var googleMap: GoogleMap? = null
  private lateinit var geocoder: Geocoder
  private lateinit var myRegistryList: List<MovieRegistry>
  private var markers = mutableListOf<MarkerData>()

  @SuppressLint("MissingPermission")
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    myRegistryList = emptyList()

    MovieRepository.getInstance().getMoviesList { result ->
      result.getOrNull().let {
        if (it != null) {
          myRegistryList = it
        }
      }
    }

    val view = inflater.inflate(R.layout.fragment_map, container, false)

    binding = FragmentMapBinding.bind(view)
    mapView = binding.mapView
    mapView.onCreate(savedInstanceState)

    mapView.getMapAsync { map ->
      googleMap = map
      googleMap?.isMyLocationEnabled = true
      FusedLocation.registerListener(this)

      myRegistryList.map { registry ->
        val location = LatLng(registry.cinema.latitude, registry.cinema.longitude)
        val marker = googleMap?.addMarker(
          MarkerOptions()
            .position(location)
            .icon(
              BitmapDescriptorFactory.defaultMarker(
                registry.rateColor())
            )

            .title(registry.movie.name)
            .snippet(registry.cinema.name))

        markers.add(MarkerData(marker, registry.id))
      }

      googleMap?.setOnMarkerClickListener { marker ->
        val savedMarker = markers.find { it.marker == marker }

        if (savedMarker != null)
        {
          NavigationManager.goToFilmesDetailFragment(
            requireActivity().supportFragmentManager,
            savedMarker?.registryId.toString()
          )
        }

        true
      }
    }


    googleMap?.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->
      val savedMarker = markers.find { it.marker == marker }

      if (savedMarker != null)
      {
        NavigationManager.goToFilmesDetailFragment(
          requireActivity().supportFragmentManager,
          savedMarker?.registryId.toString()
        )
      }

      true
    })

    geocoder = Geocoder(context, Locale.getDefault())

    return view
  }

  override fun onLocationChanged(latitude: Double, longitude: Double) {
    placeCamera(latitude, longitude)
  }

  private fun placeCamera(latitude: Double, longitude: Double) {
    val cameraPosition = CameraPosition.Builder()
      .target(LatLng(latitude, longitude))
      .zoom(12f)
      .build()

    googleMap?.animateCamera(
      CameraUpdateFactory.newCameraPosition(cameraPosition))
  }



  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    mapView.onResume()
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
    FusedLocation.unregisterListener()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}
