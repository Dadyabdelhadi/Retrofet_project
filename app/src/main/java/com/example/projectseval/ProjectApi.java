package com.example.projectseval;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProjectApi {
    String BASE_URL ="https://api-projects-qcon.onrender.com/api/";
    @GET("getAll")
    Call<List<Project>> getProjects();
    @GET("getOne/{id}")
    Call<List<Project>> findProject(@Path("id") String id);
    @POST("post")
    Call<Project> createProject(@Body Project project);

    @PATCH("update/{id}")
    Call<Project> updateProject(@Path("id") String id, @Body Project project);

    @DELETE("delete/{id}")
    Call<Void> deleteProject(@Path("id") String id);

}
