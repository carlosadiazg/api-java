package com.davivienda.monedero.service;

import com.davivienda.monedero.model.MonederoModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
public class MonederoService {

    @Value("${app.endpoint}")
    private String endPoint;

    public MonederoModel getMonedero(String idMonedero) throws Exception {

        MonederoModel monederoModel = null;
        ResponseEntity<Map> response;
        response = searchMonedero(idMonedero);
        JSONObject jObject = new JSONObject(response.getBody());

        if (!jObject.toString().equals("{}")) {

            String id = obtenerDato(jObject, "id");
            String propietario = obtenerDato(jObject, "propietario");
            float saldo = Float.valueOf(obtenerDato(jObject, "saldo"));
            Date fechaCreacion = null;
            Date fechaModificacion = null;

            try {
                fechaCreacion = new SimpleDateFormat("yyyy-MM-d H:m:s").parse(obtenerDato(jObject, "fechaCreacion"));
                fechaModificacion = new SimpleDateFormat("yyyy-MM-d H:m:s").parse(obtenerDato(jObject, "fechaModificacion"));
            } catch (ParseException e) {
                throw new Exception("Error al procesar la respuesta");
            }

            monederoModel = new MonederoModel(id, propietario, saldo, fechaCreacion, fechaModificacion);
        }
        return monederoModel;
    }

    private ResponseEntity<Map> searchMonedero(String idMonedero) throws Exception {

        ResponseEntity<Map> response;

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.setConnectTimeout(Duration.ofMillis(10000)).setReadTimeout(Duration.ofMillis(10000));
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestHeaders = new HttpEntity<String>(headers);

        try {
            response = restTemplate.exchange(endPoint + idMonedero, HttpMethod.GET, requestHeaders, Map.class);
        } catch (HttpClientErrorException e) {
            throw new Exception(e);
        }

        return response;
    }

    private String obtenerDato(JSONObject object, String index) {
        try {
            return object.getString(index);
        } catch (JSONException je) {
            return "";
        }
    }
}