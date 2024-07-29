package com.example.projectoneecommerecemvvm.model

import androidx.core.location.LocationRequestCompat.Quality
import com.example.projectoneecommerecemvvm.model.data.smartPhone.GetSmartPhoneDetailedInfoResponse

data class GetCartDetails(
    val productInfo:GetSmartPhoneDetailedInfoResponse,
    val quality: Int
)
