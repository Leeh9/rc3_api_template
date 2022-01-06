package com.example.rc3_api_template.src.main.map


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.rc3_api_template.R
import com.example.rc3_api_template.MarkerEventListener
import com.example.rc3_api_template.config.BaseFragment
import com.example.rc3_api_template.src.main.weather.WeatherActivity
import com.example.rc3_api_template.databinding.FragmentMapBinding
import com.example.rc3_api_template.src.main.map.models.BikeResponse
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::bind, R.layout.fragment_map),
    MapFragmentInterface {
    private val mapViewContainer: ViewGroup? = null
    private val eventListener = getContext()?.let { MarkerEventListener(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //지도
        val mapView = MapView(getActivity())
        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)

        mapView.setPOIItemEventListener(eventListener)

        // 중심점 변경 - 서울 남산
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304),
            true
        )

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true)

        showLoadingDialog(requireContext())
        BikeService(this).tryGetBike(mapView)

//        binding.ibCurLoc.setOnClickListener {
//            showLoadingDialog(requireContext())
//            BikeService(this).tryGetBike(mapView)
//        }
        binding.ibWeather.setOnClickListener {
            activity?.let{
                val intent = Intent(context, WeatherActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onGetBikeSuccess(response: BikeResponse, mapView: MapView) {
        dismissLoadingDialog()
        val marker = MapPOIItem()
        for (i: Int in 1..999) {
            marker.itemName = response.rentBikeStatus.row[i].stationName
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(
                response.rentBikeStatus.row[i].stationLatitude.toDouble(),
                response.rentBikeStatus.row[i].stationLongitude.toDouble()
            )   // 좌표
            marker.markerType = MapPOIItem.MarkerType.BluePin          // 마커 모양
            marker.selectedMarkerType =
                MapPOIItem.MarkerType.RedPin  // 클릭 시 마커 모양
            marker.tag = 0
            mapView.addPOIItem(marker)
        }
    }

    override fun onGetBikeFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onStop() {
        super.onStop()
        if (mapViewContainer != null) {
            mapViewContainer.removeAllViews()
        }
    }

}


