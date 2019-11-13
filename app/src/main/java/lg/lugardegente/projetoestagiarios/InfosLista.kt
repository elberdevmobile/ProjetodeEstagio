package com.example.elber.tilixelber

import android.nfc.Tag
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.http.Path
import java.io.Serializable
import java.util.*



    data class InfosLista(
           @SerializedName("data") var informacoes: ArrayList<ResponseListaInfos>


)






