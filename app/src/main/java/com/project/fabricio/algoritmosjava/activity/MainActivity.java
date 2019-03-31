package com.project.fabricio.algoritmosjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.fabricio.algoritmosjava.R;
import com.project.fabricio.algoritmosjava.adapter.AdapterVideo;
import com.project.fabricio.algoritmosjava.api.YoutubeService;
import com.project.fabricio.algoritmosjava.helper.RetrofitConfig;
import com.project.fabricio.algoritmosjava.helper.YouTubeConfig;
import com.project.fabricio.algoritmosjava.listener.RecyclerItemClickListener;
import com.project.fabricio.algoritmosjava.model.Item;
import com.project.fabricio.algoritmosjava.model.Resultado;
import com.project.fabricio.algoritmosjava.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVideos;
    private MaterialSearchView searchView;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideo adapterVideo;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVideos = findViewById(R.id.reciclerVideos);
        searchView = findViewById(R.id.searchView);

        retrofit = RetrofitConfig.getRetrofit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Algoritmos em JAVA");
        setSupportActionBar(toolbar);


        recuperarVideos("");


        //métodos para SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recuperarVideos( query );
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            //Quando o usuário abrir o searchview
            @Override
            public void onSearchViewShown() {

            }
            //Quando o usuário fechar o searchview
            @Override
            public void onSearchViewClosed() {
                recuperarVideos("" );
            }
        });

    }



    private void recuperarVideos(String pesquisa) {

        //A pesquisa do usuário precisa não pode conter espaço. Existindo, tem que substituir por +
        String q = pesquisa.replaceAll(" ", "+");

        YoutubeService youtubeService = retrofit.create( YoutubeService.class );


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
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.i("----RESULTADO----", "resultado: " + response.toString());

                if ( response.isSuccessful() ) {
                    resultado = response.body();
                    videos = resultado.items;
                    configurarRecyclerView();
                    //Log.i("----RESULTADO----", "resultado: "  );
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

    }


    public void configurarRecyclerView() {
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager( new LinearLayoutManager( this ));
        recyclerVideos.setAdapter(adapterVideo);

        recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Item video = videos.get(position);
                                String idVideo = video.id.videoId;

                                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                                intent.putExtra("idVideo", idVideo);
                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

        return true;
    }
}
