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
import android.widget.EditText;
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

public class Ricerca_Libri extends AppCompatActivity{
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<Libri> productList;
    private RequestQueue queue;
    private Button home;
    private Button cerca;
    private Button ricerca;
   private EditText camporicerca;
private Button carrello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ricerca_libri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.setComponents();
    }


    public void setComponents() {
        this.context = this;
        this.queue = Volley.newRequestQueue(this.context);

        this.productList = new ArrayList<>();
        this.recyclerView = findViewById(R.id.rv);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.camporicerca = findViewById(R.id.camporicerca);
        this.ricerca = findViewById(R.id.ricerca);

        this.home=findViewById(R.id.home);
        this.cerca=findViewById(R.id.cerca);
        this.carrello=findViewById(R.id.carrello);

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

        this.ricerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerca();
            }
        });
    }

    private void carrelloClick(int id){

        StringRequest request = new StringRequest(Request.Method.GET, "https://laggetta.altervista.org/Sito_App_Libri/api_carrello.php?add="+id+"&quantita=1",
                (response) -> {
                    Toast.makeText(context, "prodotto aggiunto al carrello ", Toast.LENGTH_SHORT).show();
                },
                (err) -> {
                    Log.e("ERROR", err.getMessage());
                });
        this.queue.add(request);
    }

    private void cerca(){
        this.productList.clear();
        String parola = this.camporicerca.getText().toString().trim().toLowerCase();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://laggetta.altervista.org/Sito_App_Libri/api.php?term=" + parola, null,
                (response) -> {
                    try {
                        JSONArray data = response.getJSONArray("libri");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            productList.add(new Libri(
                                    obj.getInt("codice"),
                                    obj.getString("urlCopertina"),
                                    obj.getString("titolo"),
                                    obj.getString("autore"),
                                    obj.getString("trama"),
                                    obj.getDouble("prezzo")
                            ));
                        }

                        recyclerView.setAdapter(new LibriAdapter(productList, new LibriAdapter.LibroClick() {
                            @Override
                            public void clickBottone(int codice) {
                                carrelloClick(codice);
                            }
                        }));
                    } catch (Exception err){
                        Log.e("ERROR", err.getMessage());
                    }
                },
                (err) -> {
                    Log.e("ERROR", err.getMessage());
                });
        this.queue.add(request);
    }
}