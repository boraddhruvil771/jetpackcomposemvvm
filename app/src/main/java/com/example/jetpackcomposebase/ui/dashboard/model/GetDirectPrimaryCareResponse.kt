package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class GetDirectPrimaryCareResponse(
    @JsonProperty("commonContact")
    var commonContact: String? = null,
    @JsonProperty("primaryCare")
    var primaryCare: List<AvailableServicesDataModel?>? = null,
    @JsonProperty("primaryCareDashBoardItems")
    var primaryCareDashBoardItems: List<AvailableServicesDataModel?>? = null
)
