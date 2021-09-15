package com.davivienda.monedero.service;

import com.davivienda.monedero.model.MonederoModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import io.micrometer.core.instrument.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

@SpringBootTest
public class MonederoServiceTest {
    @Test
    void getMonedero() {
        MonederoService ms = new MonederoService();
        MonederoModel md = ms.getMonedero("1");
        Assertions.assertEquals("Carlos Díaz", md.getPropietaro());
    }

    @Test
    void searchMonedero() {
        JSONObject json;
        //MonederoService ms = new MonederoService();
        //MonederoModel md = ms.getMonedero("1");

        try{
            File f = ResourceUtils.getFile("classpath:response.json");
            if(f.exists()){
                InputStream is = new FileInputStream(f);
                json = new JSONObject(IOUtils.toString(is, Charset.defaultCharset()));
                JSONArray jarray = json.getJSONArray("result");

                for(int i = 0; i<json.getJSONArray("result").length();i++){
                    JSONObject objects = jarray.getJSONObject(i);
                    String idMonedero = (String) objects.get("idMonedero");
                    if(idMonedero.equals("1")){
                        System.out.println("El monedero tiene " + objects.get("saldo"));
                        Assertions.assertEquals("100000", objects.get("saldo").toString());
                    }
                }
            }
        }catch(FileNotFoundException | JSONException e){
            e.printStackTrace();
        }
    }
}