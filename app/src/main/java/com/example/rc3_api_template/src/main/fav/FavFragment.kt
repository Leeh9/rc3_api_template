package com.example.rc3_api_template.src.main.fav

import android.os.Bundle
import android.view.View
import com.example.rc3_api_template.config.BaseFragment
import com.example.rc3_api_template.R
import com.example.rc3_api_template.databinding.FragmentFavBinding


class FavFragment:
    BaseFragment<FragmentFavBinding>(FragmentFavBinding::bind, R.layout.fragment_fav) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}