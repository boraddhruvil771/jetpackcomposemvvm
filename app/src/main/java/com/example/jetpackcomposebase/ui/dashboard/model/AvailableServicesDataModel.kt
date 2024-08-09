package com.example.jetpackcomposebase.ui.dashboard.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AvailableServicesDataModel(
    @JsonProperty("id")
    var id: Int? = null,
    @JsonProperty("name")
    var name: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(id!!)
        p0.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<AvailableServicesDataModel> {
        override fun createFromParcel(parcel: Parcel): AvailableServicesDataModel {
            return AvailableServicesDataModel(parcel)
        }

        override fun newArray(size: Int): Array<AvailableServicesDataModel?> {
            return arrayOfNulls(size)
        }
    }

}
