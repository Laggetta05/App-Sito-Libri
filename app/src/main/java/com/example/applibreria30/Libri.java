package com.example.applibreria30;

public class Libri {
    private int codice;
    private String copertinaURL;
    private String titolo;
    private String autore;
    private String trama;
    private double prezzo;


    public Libri(int codice, String copertinaURL, String titolo, String autore, String trama, double prezzo) {
        this.codice = codice;
        this.copertinaURL = copertinaURL;
        this.titolo= titolo;
        this.autore= autore;
        this.trama = trama;
        this.prezzo = prezzo;
    }

    public int getCodice() {
        return codice;
    }

    public String getCopertinaURL() {
        return copertinaURL;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getTrama() {
        return trama;
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "codice=" + codice +
                ", copertinaURL='" + copertinaURL + '\'' +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", trama='" + trama + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
