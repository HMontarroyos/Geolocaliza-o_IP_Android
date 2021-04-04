package com.example.geolocalizacao;

public class Localizacao {

    private String country_code;
    private String country_name;

    public Localizacao(String code, String name){
        this.country_code = code;
        this.country_name = name;
    }

    public String getCountry_code() {return this.country_code;}
    public String getCountry_name() {return this.country_name;}


}
