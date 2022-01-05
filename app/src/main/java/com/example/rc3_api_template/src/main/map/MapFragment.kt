package com.example.rc3_api_template.src.main.map


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//class MapFragment : Fragment() {
//
//
//    private lateinit var binding : FragmentMapBinding
//    private val mapViewContainer: ViewGroup? = null
//    private val eventListener = getContext()?.let { MarkerEventListener(it) }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentMapBinding.inflate(inflater,
//            container, false)
//
//        //지도
//        val mapView = MapView(getActivity())
//        val mapViewContainer = binding.mapView as ViewGroup
//        mapViewContainer.addView(mapView)
//
//        mapView.setPOIItemEventListener(eventListener)
//
//        // 중심점 변경 - 서울 남산
//        mapView.setMapCenterPoint(
//            MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304),
//            true
//        )
//
//        // 줌 레벨 변경
//        mapView.setZoomLevel(4, true)
//
//        //마커 찍기
////        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304)
////        val marker = MapPOIItem()
////        marker.itemName = "서울 남산"
////        marker.tag = 0
////        marker.mapPoint = MARKER_POINT
////        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
////        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
////        mapView.addPOIItem(marker)
//
//
//
//        binding.ibWeather.setOnClickListener {
//            activity?.let{
//                val intent = Intent(context, WeatherActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        getBikeData(mapView)
//
//        return binding!!.root
//    }
//
//
//
//    override fun onStop() {
//        super.onStop()
//        if (mapViewContainer != null) {
//            mapViewContainer.removeAllViews()
//        }
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance() : MapFragment {
//            return MapFragment()
//        }
//
//    }
//
//    private fun getBikeData(mapView: MapView) {
//        val bikeInterface = RetrofitBikeClient.sRetrofit.create(BikeInterface::class.java)
//        bikeInterface.getBike().enqueue(object : Callback<BikeResponse> {
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(
//                call: Call<BikeResponse>,
//                response: Response<BikeResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val result = response.body() as BikeResponse
//
//                    val marker = MapPOIItem()
//                    for(i: Int in 1..999) {
//                        marker.itemName = result.rentBikeStatus.row[i].stationName
//                        marker.mapPoint = MapPoint.mapPointWithGeoCoord(
//                            result.rentBikeStatus.row[i].stationLatitude.toDouble(),
//                            result.rentBikeStatus.row[i].stationLongitude.toDouble()
//                        )   // 좌표
//                        marker.markerType = MapPOIItem.MarkerType.BluePin          // 마커 모양
//                        marker.selectedMarkerType =
//                            MapPOIItem.MarkerType.RedPin  // 클릭 시 마커 모양
//                        marker.tag = 0
//                        mapView.addPOIItem(marker)
//                    }
//
//                } else {
//                    Log.d("MapFragment", "Error")
//                }
//            }
//
//            override fun onFailure(call: Call<BikeResponse>, t: Throwable) {
//                Log.d("MapFragment", t.message ?: "통신오류")
//            }
//        })
//    }
//
//}


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


