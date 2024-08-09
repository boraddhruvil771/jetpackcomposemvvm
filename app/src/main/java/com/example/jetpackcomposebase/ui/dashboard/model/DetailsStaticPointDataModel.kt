package com.app.encorehealthcare.ui.directPrimaryCare.models

import com.google.gson.annotations.SerializedName

data class DetailsStaticPointDataModel(
    @SerializedName("image") var image: Int = -1,
    @SerializedName("title") var title: String = "",
)
