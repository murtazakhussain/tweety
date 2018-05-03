package com.tweety.code.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tweets {
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("id")
    @Expose
    var id: Long? = null

    @SerializedName("id_str")
    @Expose
    var idStr: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("truncated")
    @Expose
    var truncated: Boolean? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("lang")
    @Expose
    var lang: String? = null
}
