package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.annotations.SerializedName

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class NotificationData(
    @SerializedName("count")
    var count: String? = null,
    @SerializedName("notifications")
    var notifications: List<NotificationItem?>? = null
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class NotificationItem(
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("user_id")
        var userId: Int? = null,
        @SerializedName("notification_id")
        var notification_id: Int? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("redirect_id")
        var redirect_id: String? = null,
        @SerializedName("topic")
        var topic: String? = null,
        @SerializedName("type")
        var type: String? = null,
        @SerializedName("redirect_keyword")
        var redirect_keyword: String? = null,
        @SerializedName("redirect_link")
        var redirect_link: String? = null,
        @SerializedName("notification_is_read")
        var notification_is_read: Boolean? = null,
        @SerializedName("createdAt")
        var createdAt: String? = null,
        @SerializedName("updatedAt")
        var updatedAt: String? = null
    )
}
