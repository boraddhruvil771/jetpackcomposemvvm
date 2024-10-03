package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.annotations.SerializedName

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DocumentsResponseModel : ArrayList<DocumentsResponseModel.DocumentsResponseModelItem>() {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class DocumentsResponseModelItem(
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("fileName")
        var fileName: String? = "",
        @SerializedName("fileType")
        var fileType: String? = "",
        @SerializedName("en_link")
        var en_link: String? = "",
        @SerializedName("es_link")
        var es_link: String? = "",
        @SerializedName("createdAt")
        var createdAt : String?="",
        @SerializedName("updatedAt")
        var updatedAt : String?="",
        @SerializedName("thumbnail_link")
        var thumbnail_link :String?=null
    )
}
