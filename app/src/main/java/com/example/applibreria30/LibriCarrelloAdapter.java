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

public class LibriCarrelloAdapter extends RecyclerView.Adapter<LibriCarrelloAdapter.ViewHolder> {
    private static final String IMG_ADAPTER = "https://francescoarzilli3g.altervista.org/libbbreria/api/tools/img/index.php?url=";
    private ArrayList<LibriCarrello> libri;

    public LibriCarrelloAdapter(ArrayList<LibriCarrello> libri){
        this.libri = libri;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.libri_carrello_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LibriCarrello product = libri.get(position);
        holder.img.loadUrl(IMG_ADAPTER + product.getCopertinaURL());
        holder.titolo.setText(product.getTitolo());
        holder.prezzo.setText(product.getPrezzo() + "€");
        holder.quantita.setText("quantita: "+product.getQuntita());
    }

    @Override
    public int getItemCount() {
        return libri.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        WebView img;
        TextView titolo;
        TextView prezzo;
        TextView quantita;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img);
            this.titolo = itemView.findViewById(R.id.titolo);
            this.prezzo = itemView.findViewById(R.id.prezzo);
            this.quantita = itemView.findViewById(R.id.quantita);
            this.cardView = (CardView) itemView;
        }
    }
}
