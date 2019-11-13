package com.example.elber.tilixelber

import com.google.gson.annotations.SerializedName

data class Tags(

        @SerializedName("id") var id: Int,
        @SerializedName("label") var label: String

)