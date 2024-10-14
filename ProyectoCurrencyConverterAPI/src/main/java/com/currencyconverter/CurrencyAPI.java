package com.currencyconverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyAPI {

    private static final String API_KEY = "-"; //INGRESE AQUI SU API KEY
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

    public double getConversionRate(String from, String to) {
        String endpoint = BASE_URL + "/pair/" + from + "/" + to;
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { // 200 OK
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parseamos el JSON de la respuesta
                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getDouble("conversion_rate");
            } else {
                System.out.println("Error: No se pudo obtener la tasa de conversión. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0.0; // Devolver 0 si hubo un problema
    }
}
