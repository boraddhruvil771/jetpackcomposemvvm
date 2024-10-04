package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyNotificationRequest(
    @JsonProperty("status")
    var status: String? = null,
    @JsonProperty("userSSN")
    var userSSN: String? = null,
    @JsonProperty("id")
    var id: String? = null
)
