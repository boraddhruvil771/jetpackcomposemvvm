package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class GetPediatricTelemedicineResponse(
    @JsonProperty("commonContact")
    var commonContact: String? = null,
    @JsonProperty("specialities")
    var specialities: List<AvailableServicesDataModel?>? = null,
    @JsonProperty("specialitiesDashBoardItems")
    var specialitiesDashBoardItems : List<AvailableServicesDataModel?>? = null
)
