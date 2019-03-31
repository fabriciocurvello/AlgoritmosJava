package com.project.fabricio.algoritmosjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.project.fabricio.algoritmosjava.R
import com.project.fabricio.algoritmosjava.model.Item
import com.squareup.picasso.Picasso

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.project.fabricio.algoritmosjava.adapter.AdapterVideo.MyViewHolder

class AdapterVideo(videos: List<Item>, private val context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    private var videos = ArrayList<Item>()

    init {
        this.videos = videos as ArrayList<Item>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_video, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val video = videos[position]
        holder.titulo.text = video.snippet!!.title

        val url = video.snippet!!.thumbnails!!.high!!.url

        Picasso.get().load(url).resize(1000, 800).into(holder.capa)

    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titulo: TextView
        var descricao: TextView? = null
        var data: TextView? = null
        var capa: ImageView

        init {

            titulo = itemView.findViewById(R.id.textTitulo)
            capa = itemView.findViewById(R.id.imageCapa)
        }

    }


}
