package com.example.crudusuario.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudusuario.R;
import com.example.crudusuario.dto.DtoLogin;
import com.example.crudusuario.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_login extends AppCompatActivity {

     private static final String TAG = "activity_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logar(View view) {

        String email = ((EditText)findViewById(R.id.et_login_email)).getText().toString();
        String senha = ((EditText)findViewById(R.id.et_login_senha)).getText().toString();

        DtoLogin dto = new DtoLogin();

        dto.setEmail(email);
        dto.setPassword(senha);

        RetrofitService.getServico(this).login(dto).enqueue(new Callback<DtoLogin>() {
            @Override
            public void onResponse(Call<DtoLogin> call, Response<DtoLogin> response) {
                Toast.makeText(activity_login.this, "Usuario Logado. ", Toast.LENGTH_SHORT).show();

                SharedPreferences sp = getSharedPreferences("dados", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token",response.body().getToken());
                editor.apply();
                startActivity(new Intent( activity_login.this , MainActivity.class));
            }

            @Override
            public void onFailure(Call<DtoLogin> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
