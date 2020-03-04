package com.example.helpcare.Classes;

import com.google.android.gms.maps.model.LatLng;

public class Tasks {

    private String ImePrezime;
    private String Adresa;
    private String Broj;
    private String Dijagnoza;
    private String Terapija;
    private LatLng latLng;

    public Tasks(String ImePrezime, String Adresa, String Broj, String Dijagnoza, String Terapija, LatLng latLng) {
        this.ImePrezime = ImePrezime;
        this.Adresa = Adresa;
        this.Broj = Broj;
        this.Terapija = Terapija;
        this.Dijagnoza = Dijagnoza;
        this.latLng = latLng;
    }

    public String getAdresa() {
        return Adresa;
    }

    public String getBroj() {
        return Broj;
    }

    public String getDijagnoza() {
        return Dijagnoza;
    }

    public String getImePrezime() {
        return ImePrezime;
    }

    public String getTerapija() {
        return Terapija;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
