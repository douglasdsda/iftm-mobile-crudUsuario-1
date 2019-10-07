package com.example.crudusuario.services;

import com.example.crudusuario.dto.DtoLogin;
import com.example.crudusuario.dto.DtoUsers;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceDeServicos {

    @POST("/users")
    Call<DtoUsers> cadastroUsuario(@Body DtoUsers dto);

    @POST("/auth/login")
    Call<DtoLogin> login(@Body DtoLogin dto);

    @GET("/users")
    Call<List<DtoUsers>> todosUsuarios(@Header("Authorization") String authorization);
}
