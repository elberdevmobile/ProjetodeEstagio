package com.example.elber.tilixelber

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class ResponseListaInfos(@SerializedName("title") var title: String,
                              @SerializedName("authors") var authors: String,
                              @SerializedName("website") var website: String,
                              @SerializedName("date") var date: String,
                              @SerializedName("content") var content: String,
                              @SerializedName("image_url") var image_url: String,
                              @SerializedName("tags") var tags: ArrayList<Tags>)



