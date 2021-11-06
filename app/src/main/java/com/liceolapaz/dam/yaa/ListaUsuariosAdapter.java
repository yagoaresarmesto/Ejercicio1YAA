package com.liceolapaz.dam.yaa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder> {
    ArrayList<Usuarios> listaUsuarios;

    public ListaUsuariosAdapter(ArrayList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario, null, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {

        holder.viewNombre.setText(listaUsuarios.get(position).getNombre());
        holder.viewIdioma.setText(listaUsuarios.get(position).getIdioma());
        holder.viewEdad.setText(listaUsuarios.get(position).getEdad());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewIdioma, viewEdad;


        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewIdioma = itemView.findViewById(R.id.viewIdioma);
            viewEdad = itemView.findViewById(R.id.viewEdad);
        }
    }
}
