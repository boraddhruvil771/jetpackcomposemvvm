package com.example.jetpackcomposebase.ui.login.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponseModel(
    @JsonProperty("access_token")
    var accessToken: String? = null,
    @JsonProperty("company_id")
    var companyId: Int? = null,
    @JsonProperty("deviceToken")
    var deviceToken: String? = null,
    @JsonProperty("deviceType")
    var deviceType: String? = null,
    @JsonProperty("id")
    var id: Int? = null,
    @JsonProperty("status")
    var status: String? = null,
    @JsonProperty("userAddress")
    var userAddress: String? = null,
    @JsonProperty("userBirthDate")
    var userBirthDate: String? = null,
    @JsonProperty("userCity")
    var userCity: String? = null,
    @JsonProperty("userCompanyName")
    var userCompanyName: String? = null,
    @JsonProperty("userContact")
    var userContact: String? = null,
    @JsonProperty("userContact2")
    var userContact2: Any? = null,
    @JsonProperty("userEmail")
    var userEmail: String? = null,
    @JsonProperty("userEmpType")
    var userEmpType: String? = null,
    @JsonProperty("userFirstName")
    var userFirstName: String? = null,
    @JsonProperty("userGender")
    var userGender: String? = null,
    @JsonProperty("userIsVerified")
    var userIsVerified: Int? = null,
    @JsonProperty("userJobTitle")
    var userJobTitle: String? = null,
    @JsonProperty("userLastName")
    var userLastName: String? = null,
    @JsonProperty("userLocation")
    var userLocation: String? = null,
    @JsonProperty("userParent_ID")
    var userParentID: Int? = null,
    @JsonProperty("userProfilePic")
    var userProfilePic: String? = null,
    @JsonProperty("userRealation")
    var userRealation: String? = null,
    @JsonProperty("userSSN")
    var userSSN: String? = null,
    @JsonProperty("parentUserSSN")
    var parentUserSSN: String? = null,
    @JsonProperty("userState")
    var userState: String? = null,
    @JsonProperty("userStatus")
    var userStatus: String? = null,

    @JsonProperty("userTiear")
    var userTiear: Int? = null,
    @JsonProperty("store_version")
    var store_version: String? = null,
    @JsonProperty("isForceUpdate")
    var isForceUpdate: Boolean? = null,
    @JsonProperty("userZip")
    var userZip: String? = null,
    @JsonProperty("latestVersion")
    var latestVersion: String? = null,
    @JsonProperty("minVersion")
    var minVersion: String? = null,

    @JsonProperty("has_medical_plan")
    var hasMedicalPlan: Boolean? = null,

    @JsonProperty("userParentTiear")
    var userParentTiear: Int? = null
)
