package com.project.fabricio.algoritmosjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.project.fabricio.algoritmosjava.R;
import com.project.fabricio.algoritmosjava.adapter.AdapterVideo;
import com.project.fabricio.algoritmosjava.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVideos;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVideos = findViewById(R.id.reciclerVideos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Algoritmos em JAVA");
        setSupportActionBar(toolbar);


        recuperarVideos();
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager( new LinearLayoutManager( this ));
        recyclerVideos.setAdapter(adapterVideo);
    }

    private void recuperarVideos() {

        Video video1 = new Video();
        video1.setTitulo("Vídeo 1 de Algoritmos do Canal do Fabrício");
        videos.add(video1);

        Video video2 = new Video();
        video2.setTitulo("Vídeo 2 de Algoritmos do Canal do Fabrício");
        videos.add(video2);
    }
}
