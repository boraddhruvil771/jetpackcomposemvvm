package com.example.jetpackcomposebase.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateFCMTokenRequest(
    @JsonProperty("deviceToken")
    var deviceToken: String? = null,
    @JsonProperty("deviceType")
    var deviceType: String? = null
)
