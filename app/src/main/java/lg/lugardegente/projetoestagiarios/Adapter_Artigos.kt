package com.example.elber.tilixelber

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.widget.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_artigos.view.*
import lg.lugardegente.projetoestagiarios.R
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URI


class Adapter_Artigos(val list: List<ResponseListaInfos>)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: ResponseListaInfos = list[position]
        holder.bind(movie)
    }



    override fun getItemCount(): Int = list.size

}


class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_artigos, parent, false)), View.OnClickListener {
    override fun onClick(v: View?) {
        val settings: SharedPreferences
        settings = itemView.context.getSharedPreferences("Artigoprefs",Context.MODE_PRIVATE)
        val editor = settings.edit()
        if(check!!.isChecked){
            editor.putBoolean("lido",true)
        }else{
            editor.putBoolean("lido",false)
        }
        editor.commit()
    }

    private var mTitleView: TextView? = null
    private var mId: TextView? = null
    private var mContent: TextView? = null
    private var mWebsite: TextView? = null
    private var mCategoria: TextView? = null
    private var mImage: ImageView? = null
    private var mAutor: TextView? = null
    private var mdata: TextView? = null
    private var titulo: TextView? = null
    private var texto: TextView? = null
    private var check: CheckBox? = null
    private var lido: Boolean = false



    init {
        mTitleView = itemView.tx_titulo
        mWebsite = itemView.tx_website
        mdata = itemView.tx_data
        mAutor = itemView.tx_autor
        texto = itemView.tx_contentartigo
        mImage = itemView.imageView
        check = itemView.checkBox

    }

    fun bind(movie: ResponseListaInfos) {
        mTitleView?.text = movie.title
        mWebsite?.text = movie.website
        mdata?.text = movie.date
        mAutor?.text = movie.authors
        texto?.text = movie.content
        mImage?.let { Glide.with(itemView.context).load(movie.image_url).into(it) };
        val settings: SharedPreferences
        settings = itemView.context.getSharedPreferences("Artigoprefs",Context.MODE_PRIVATE)
        lido = settings.getBoolean("lido",false)
        check!!.isChecked = lido





        val configuracao = RequestOptions()
            .centerInside()
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .centerInside()
            .priority(Priority.HIGH)

        val configuracaofalha = RequestOptions()
            .centerInside()
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .centerInside()
            .priority(Priority.HIGH)


        check!!.setOnClickListener(this)






    }


}