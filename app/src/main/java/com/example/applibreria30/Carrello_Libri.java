package com.example.applibreria30;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Carrello_Libri extends AppCompatActivity{
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<LibriCarrello> productList;
    private RequestQueue queue;

    private Button home,cerca,carrello,svuota_carrello,procedi_all_acquisto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrello_libri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.setComponents();
        this.mostra_prodotto();
    }
private void mostra_prodotto (){
        productList.clear();
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://laggetta.altervista.org/Sito_App_Libri/api_carrello.php?print", null,
            (response) -> {
                try {
                    JSONArray data = response.getJSONArray("libri");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        productList.add(new LibriCarrello(
                                obj.getInt("codice"),
                                obj.getString("urlCopertina"),
                                obj.getString("titolo"),
                                obj.getInt("quantita"),
                                obj.getDouble("prezzo")
                        ));
                    }

                    recyclerView.setAdapter(new LibriCarrelloAdapter(productList));
                } catch (Exception err){
                    Log.e("ERROR", err.getMessage());
                }
            },
            (err) -> {
                Log.e("ERROR", err.getMessage());
            });
    this.queue.add(request);
}

    public void setComponents() {
        this.context = this;
        this.queue = Volley.newRequestQueue(this.context);

        this.productList = new ArrayList<>();
        this.recyclerView = findViewById(R.id.rv);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.home=findViewById(R.id.home);
        this.cerca=findViewById(R.id.cerca);
        this.carrello=findViewById(R.id.carrello);
        this.svuota_carrello=findViewById(R.id.svuota);
        this.procedi_all_acquisto=findViewById(R.id.acquisto);

        this.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ListaLibri.class ));
            }
        });

        this.cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Ricerca_Libri.class ));
            }

        });

        this.carrello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Carrello_Libri.class ));
            }

        });

        this.svuota_carrello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svuota_carrello();
            }

        });

        this.procedi_all_acquisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acquista();
            }

        });
    }
    private void acquista(){

        StringRequest request = new StringRequest(Request.Method.GET, "https://laggetta.altervista.org/Sito_App_Libri/api_carrello.php?acquista",
                (response) -> {
                    Toast.makeText(context, "Libri acquistati con successo  ", Toast.LENGTH_SHORT).show();
                mostra_prodotto();
                    },
                (err) -> {
                    Log.e("ERROR", err.getMessage());
                });
        this.queue.add(request);
    }

    private void svuota_carrello(){

        StringRequest request = new StringRequest(Request.Method.GET, "https://laggetta.altervista.org/Sito_App_Libri/api_carrello.php?remove",
                (response) -> {
                    Toast.makeText(context, "Carrello svuotato con successo", Toast.LENGTH_SHORT).show();
                mostra_prodotto();
                    },
                (err) -> {
                    Log.e("ERROR", err.getMessage());
                });
        this.queue.add(request);
    }
}
