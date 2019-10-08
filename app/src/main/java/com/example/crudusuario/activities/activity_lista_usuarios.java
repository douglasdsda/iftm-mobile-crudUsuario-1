package com.example.crudusuario.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crudusuario.R;
import com.example.crudusuario.dto.DtoUsers;
import com.example.crudusuario.helpers.UsuarioAdapter;
import com.example.crudusuario.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_lista_usuarios extends AppCompatActivity {

    static final String TAG = "activity_lista_usuarios";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        buscaDados();
    }

    private void preencheRecyclerview(List<DtoUsers> lista) {
        RecyclerView mRecyclerView = findViewById(R.id.rv_todos_usuarios);
        UsuarioAdapter mAdapter = new UsuarioAdapter(this, lista);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void buscaDados() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        String token = sp.getString("token", null);
        Log.d(TAG, "buscaDados: " + token);
        RetrofitService.getServico(this).todosUsuarios("Bearer " + token).enqueue(new Callback<List<DtoUsers>>() {
            @Override
            public void onResponse(Call<List<DtoUsers>> call, Response<List<DtoUsers>> response) {
                if (response.code() == 200) {
                    List<DtoUsers> lista = response.body();
                    preencheRecyclerview(lista);
                } else
                    Toast.makeText(activity_lista_usuarios.this, "Problema " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<DtoUsers>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


}
