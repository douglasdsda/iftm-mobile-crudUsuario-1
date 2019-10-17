package com.example.crudusuario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudusuario.R;
import com.example.crudusuario.dto.DtoUsers;
import com.example.crudusuario.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteracaoUsuarioActivity extends AppCompatActivity {
    EditText et_nome, et_email, et_telefone;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_de_usuario);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String nome = intent.getStringExtra("nome");
        String email = intent.getStringExtra("email");
        String tel = intent.getStringExtra("tel");

        et_nome = findViewById(R.id.et_cadastro_usuario_nome);
        et_email = findViewById(R.id.et_cadastro_usuario_email);
        et_telefone = findViewById(R.id.et_cadastro_usuario_phone);

        et_nome.setText(nome);
        et_email.setText(email);
        et_telefone.setText(tel);


    }

    public void cadastrar(View view) {

        System.out.print("------------ cadastro --------------");

        String nome = ((EditText) findViewById(R.id.et_cadastro_usuario_nome)).getText().toString();
        String telefone = ((EditText) findViewById(R.id.et_cadastro_usuario_phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.et_cadastro_usuario_email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.et_cadastro_usuario_password)).getText().toString();

        DtoUsers dtoUsers;

        if(senha.isEmpty())
            dtoUsers = new DtoUsers(email, nome, senha, telefone);
        else
            dtoUsers = new DtoUsers(email, nome, senha, telefone);

        String token = getToken();
        RetrofitService.getServico(this).alterarUsuario(dtoUsers, id, "Bearer " +token).enqueue(new Callback<DtoUsers>() {
            @Override
            public void onResponse(Call<DtoUsers> call, Response<DtoUsers> response) {
                if(response.code() ==200)
                    Toast.makeText(AlteracaoUsuarioActivity.this, "Usuario Alterado com Sucesso.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AlteracaoUsuarioActivity.this, "Erro: ."+ response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DtoUsers> call, Throwable t) {

            }
        });

    }

    private String getToken() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        return sp.getString("token", null);
    }
}
