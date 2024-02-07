package com.example.lugaresfavoritos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.lugaresfavoritos.database.Persona;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Persona> datos;
    private Context context;
    private OnItemClickListener listener;
    private String imageStoragePath;

    //para contato
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecyclerAdapter(Context context, ArrayList<Persona> datos,OnItemClickListener listener) {
        this.datos = datos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_list,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Persona persona = datos.get(position);



        // Cargar la imagen si está disponible


        //para contacto
        final int clickedPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método onItemClick del listener al hacer clic
                listener.onItemClick(clickedPosition);
            }
        });
    }





    @Override
    public int getItemCount() {

        return datos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtnombre;
        private final TextView textViewDireccion;
        private final TextView textViewTelefono;
        private  final ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.textViewNombre);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);
            imageButton = itemView.findViewById(R.id.imageButton2);

        }

        public TextView getTextView() {
            return txtnombre;
        }

        public TextView getTextViewDireccion() {
            return textViewDireccion;
        }

        public TextView getTextViewTelefono() {
            return textViewTelefono;
        }


    }

}
