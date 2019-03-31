package com.project.fabricio.algoritmosjava.api;

import com.project.fabricio.algoritmosjava.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    /*

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyD4z9O_Ze-wdxXtYqHSwYX6zUZyZ4yj0G4
    &channelId=UCDKQmex_QOtjlZvWlRbjMew
    &q=pesquisa+usuario

     */

    @GET("search")
    Call<Resultado> recuperarVideos(
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId,
            @Query("q") String q
    );

}
