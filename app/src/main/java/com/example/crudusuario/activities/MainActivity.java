package com.example.crudusuario.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudusuario.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch ( item.getItemId()) {
            case R.id.cadastro_usuario :
                startActivity(new Intent(this,activity_cadastro_de_usuario.class));
                break;

            case R.id.login:
                startActivity(new Intent(this,activity_login.class));
                break;

            case R.id.lista_usuarios:
                startActivity(new Intent(this,activity_lista_usuarios.class));
                break;
            case R.id.excluir_usuario:
                startActivity(new Intent(this,activity_delete.class));
                break;
            case R.id.editar_usuario:
                startActivity(new Intent(this,activity_update.class));
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
