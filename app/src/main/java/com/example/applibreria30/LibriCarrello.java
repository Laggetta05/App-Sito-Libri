package com.example.applibreria30;

public class LibriCarrello {
    private int codice;
    private String copertinaURL;
    private String titolo;
   private int quntita;
    private double prezzo;


    public LibriCarrello(int codice, String copertinaURL, String titolo, int quntita, double prezzo) {
        this.codice = codice;
        this.copertinaURL = copertinaURL;
        this.titolo = titolo;
        this.quntita = quntita;
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

    public int getQuntita() {
        return quntita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return "LibriCarrello{" +
                "codice=" + codice +
                ", copertinaURL='" + copertinaURL + '\'' +
                ", titolo='" + titolo + '\'' +
                ", quntita=" + quntita +
                ", prezzo=" + prezzo +
                '}';
    }
}
