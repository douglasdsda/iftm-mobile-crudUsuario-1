package com.example.crudusuario.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudusuario.R;
import com.example.crudusuario.activities.AlteracaoUsuarioActivity;
import com.example.crudusuario.activities.activity_lista_usuarios;
import com.example.crudusuario.dto.DtoUsers;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<DtoUsers> lista;
    private DtoUsers mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public UsuarioAdapter(Context context, List<DtoUsers> lista) {
        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public UsuarioAdapter.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View mItemView = mInflater.inflate(R.layout.activity_recyclerview_layout_item_todos_usuarios, parent, false);
         return new UsuarioHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioHolder holder, int position) {
        String nome = lista.get(position).getName();
        holder.nome.setText(nome);
       // Toast.makeText(context, "OLA 1---.", Toast.LENGTH_SHORT).show();
     }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public Context getContext() {
        return context;
    }

    public void deleteItem(int position) {
        Toast.makeText(context,"excluindo item", Toast.LENGTH_LONG).show();
        mRecentlyDeletedItem = lista.get(position);
        mRecentlyDeletedItemPosition = position;
        lista.remove(position);
        notifyItemRemoved(position);

    }

    //swipe
    public void showAlertDialogButtonClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Exclusão de usuário");
        builder.setMessage("Voce confirma a ação");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                undoDelete();
            }
        });

    }

    private void excluir(){
        ((activity_lista_usuarios) context)
                .excluirItem(mRecentlyDeletedItem.getId());
    }

    private void undoDelete(){
        lista.add(mRecentlyDeletedItemPosition,mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        final UsuarioAdapter usuarioAdapter;
        public final TextView nome;
       // public final ImageView delete;
        //public final ImageView update;

        public UsuarioHolder(@NonNull View itemView, UsuarioAdapter usuarioAdapter) {
            super(itemView);
            this.usuarioAdapter = usuarioAdapter;
            this.nome = itemView.findViewById(R.id.tv_recycleview_nome_usuario);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

         //q  Toast.makeText(context, "OLA 2---.", Toast.LENGTH_SHORT).show();

            DtoUsers user = lista.get(getLayoutPosition());
            String nome = user.getName();
            int id = user.getId();
            String email = user.getEmail();
            String tel = user.getPhone();
            Intent intent = new Intent(context, AlteracaoUsuarioActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nome", nome);
            intent.putExtra("email", email);
            intent.putExtra("tel", tel);
            context.startActivity(intent);
        }
    }
}
