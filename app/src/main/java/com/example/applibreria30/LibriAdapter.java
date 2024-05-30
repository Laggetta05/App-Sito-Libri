package com.example.applibreria30;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibriAdapter extends RecyclerView.Adapter<LibriAdapter.ViewHolder> {
    private static final String IMG_ADAPTER = "https://francescoarzilli3g.altervista.org/libbbreria/api/tools/img/index.php?url=";
    private ArrayList<Libri> libri;
    private LibriAdapter.LibroClick bottoneCB;
    public LibriAdapter(ArrayList<Libri> libri, final  LibriAdapter.LibroClick bottoneCB){
        this.libri = libri;
        this.bottoneCB = bottoneCB;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.libri_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Libri product = libri.get(position);
        holder.img.loadUrl(IMG_ADAPTER + product.getCopertinaURL());
        holder.titolo.setText(product.getTitolo());
        holder.autore.setText(product.getAutore());
        holder.trama.setText(product.getTrama());
        holder.prezzo.setText(product.getPrezzo() + "€");

        holder.bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              bottoneCB.clickBottone(product.getCodice());
            }
        });
    }

    @Override
    public int getItemCount() {
        return libri.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        WebView img;
        TextView titolo;
        TextView autore;
        TextView trama;
        TextView prezzo;
        Button bottone;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img);
            this.titolo = itemView.findViewById(R.id.titolo);
            this.autore = itemView.findViewById(R.id.autore);
            this.trama = itemView.findViewById(R.id.trama);
            this.prezzo = itemView.findViewById(R.id.prezzo);
            this.bottone = itemView.findViewById(R.id.bottone);
            this.cardView = (CardView) itemView;
        }
    }

    public interface LibroClick{
        public void clickBottone(int codice);
    }
}
