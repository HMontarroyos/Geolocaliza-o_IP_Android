package com.example.geolocalizacao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientGeoIP {

    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl) throws IOException {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }

        finally {
            urlConnection.disconnect();
        }
    }
    public static Localizacao localizar(String ip) throws JSONException, IOException {
        String APP_KEY = "ba75442ca8528a2461e36a25a52106a0";
        String resposta = request("http://api.ipstack.com/" + " " + ip + "?access_key="+ APP_KEY);
        JSONObject obj = new JSONObject(resposta);
        String code = obj.getString("country_code");
        String name = obj.getString("country_name");

        return new Localizacao(code, name);
    }

}
