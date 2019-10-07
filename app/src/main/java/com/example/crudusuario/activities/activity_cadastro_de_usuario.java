package com.example.crudusuario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudusuario.R;
import com.example.crudusuario.dto.DtoUsers;
import com.example.crudusuario.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_cadastro_de_usuario extends AppCompatActivity {

   private  static final String TAG = "cadastro_de_usuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_usuario);
    }

    public void cadastrar(View view) {
        String nome = ((EditText) findViewById(R.id.et_cadastro_usuario_nome)).getText().toString();
        String telefone = ((EditText) findViewById(R.id.et_cadastro_usuario_phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.et_cadastro_usuario_email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.et_cadastro_usuario_password)).getText().toString();

        DtoUsers dto = new DtoUsers(email, nome, senha, telefone);

           RetrofitService.getServico(this).cadastroUsuario(dto).enqueue(new Callback<DtoUsers>() {
               @Override
               public void onResponse(Call<DtoUsers> call, Response<DtoUsers> response) {
                //   Toast.makeText(activity_cadastro_de_usuario.this, "Usuario Cadastrado com ID: " + response.body().getId(), Toast.LENGTH_LONG).show();
            if(response.body() != null)       Toast.makeText(activity_cadastro_de_usuario.this, "Usuario Cadastrado com ID: " + response.body().getId(), Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(activity_cadastro_de_usuario.this, "Messagem de erro: " + response.errorBody(), Toast.LENGTH_LONG).show();
            }

               }

               @Override
               public void onFailure(Call<DtoUsers> call, Throwable t) {
                   Log.d(TAG, "onFailure: ");

               }
           });
    }

}
