package com.project.fabricio.algoritmosjava.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView

import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.project.fabricio.algoritmosjava.R
import com.project.fabricio.algoritmosjava.adapter.AdapterVideo
import com.project.fabricio.algoritmosjava.api.YoutubeService
import com.project.fabricio.algoritmosjava.helper.RetrofitConfig
import com.project.fabricio.algoritmosjava.helper.YouTubeConfig
import com.project.fabricio.algoritmosjava.listener.RecyclerItemClickListener
import com.project.fabricio.algoritmosjava.model.Item
import com.project.fabricio.algoritmosjava.model.Resultado

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var recyclerVideos: RecyclerView? = null
    private var searchView: MaterialSearchView? = null

    private var videos: List<Item>? = ArrayList()
    private var resultado: Resultado? = null
    private var adapterVideo: AdapterVideo? = null

    private var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerVideos = findViewById(R.id.reciclerVideos)
        searchView = findViewById(R.id.searchView)

        retrofit = RetrofitConfig.retrofit

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Algoritmos em JAVA"
        setSupportActionBar(toolbar)


        recuperarVideos("")


        //métodos para SearchView
        searchView!!.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recuperarVideos(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchView!!.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            //Quando o usuário abrir o searchview
            override fun onSearchViewShown() {

            }

            //Quando o usuário fechar o searchview
            override fun onSearchViewClosed() {
                recuperarVideos("")
            }
        })

    }


    private fun recuperarVideos(pesquisa: String) {

        //A pesquisa do usuário precisa não pode conter espaço. Existindo, tem que substituir por +
        val q = pesquisa.replace(" ".toRegex(), "+")

        val youtubeService = retrofit!!.create<YoutubeService>(YoutubeService::class.java!!)


        /* rascunho

        https://www.googleapis.com/youtube/v3/
        search
        ?part=snippet
        &order=date
        &maxResults=20
        &key=AIzaSyD4z9O_Ze-wdxXtYqHSwYX6zUZyZ4yj0G4
        &channelId=UCDKQmex_QOtjlZvWlRbjMew

         */


        youtubeService.recuperarVideos(
                "snippet", "date", "50",
                YouTubeConfig.CHAVE_YOUTUBE_API,
                YouTubeConfig.CANAL_ID, q
        ).enqueue(object : Callback<Resultado> {
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                Log.i("----RESULTADO----", "resultado: $response")

                if (response.isSuccessful) {
                    resultado = response.body()
                    videos = resultado!!.items
                    configurarRecyclerView()
                    //Log.i("----RESULTADO----", "resultado: "  );
                }
            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {

            }
        })

    }


    fun configurarRecyclerView() {
        adapterVideo = AdapterVideo(videos = this!!.videos!!, context = this)
        recyclerVideos!!.setHasFixedSize(true)
        recyclerVideos!!.layoutManager = LinearLayoutManager(this)
        recyclerVideos!!.adapter = adapterVideo

        recyclerVideos!!.addOnItemTouchListener(
                RecyclerItemClickListener(
                        this,
                        recyclerVideos!!,
                        object : RecyclerItemClickListener.OnItemClickListener {
                            override fun onItemClick(view: View, position: Int) {

                                val video = videos!![position]
                                val idVideo = video.id!!.videoId

                                val intent = Intent(this@MainActivity, PlayerActivity::class.java)
                                intent.putExtra("idVideo", idVideo)
                                startActivity(intent)

                            }

                            override fun onLongItemClick(view: View?, position: Int) {

                            }

                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                            }
                        }
                )
        )
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.menu_search)
        searchView!!.setMenuItem(item)

        return true
    }
}
