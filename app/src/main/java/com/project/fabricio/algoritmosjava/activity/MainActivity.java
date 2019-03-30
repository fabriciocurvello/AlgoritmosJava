package com.project.fabricio.algoritmosjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.project.fabricio.algoritmosjava.R;
import com.project.fabricio.algoritmosjava.adapter.AdapterVideo;
import com.project.fabricio.algoritmosjava.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVideos;
    private MaterialSearchView searchView;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVideos = findViewById(R.id.reciclerVideos);
        searchView = findViewById(R.id.searchView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Algoritmos em JAVA");
        setSupportActionBar(toolbar);


        recuperarVideos();
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager( new LinearLayoutManager( this ));
        recyclerVideos.setAdapter(adapterVideo);

        //métodos para SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
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

            }
        });

    }

    private void recuperarVideos() {

        Video video1 = new Video();
        video1.setTitulo("Vídeo 1 de Algoritmos do Canal do Fabrício");
        videos.add(video1);

        Video video2 = new Video();
        video2.setTitulo("Vídeo 2 de Algoritmos do Canal do Fabrício");
        videos.add(video2);
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
