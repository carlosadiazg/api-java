package com.davivienda.monedero.controller;

import com.davivienda.monedero.model.MonederoModel;
import com.davivienda.monedero.service.MonederoService;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class MonederoControllerTest {

    MonederoService msMock = Mockito.mock(MonederoService.class);

    @BeforeEach
    void setUp() {
        MonederoModel monederoModel;
        JSONObject json;

        try {
            File f = ResourceUtils.getFile("classpath:response.json");
            if (f.exists()) {
                InputStream is = new FileInputStream(f);
                json = new JSONObject(IOUtils.toString(is, Charset.defaultCharset()));

                String idMonedero = json.get("idMonedero").toString();
                String propietario = json.get("propietario").toString();
                float saldo = Float.valueOf(json.get("saldo").toString());
                Date fechaCreacion = new SimpleDateFormat("yyyy-MM-d H:m:s").parse(json.get("fechaCreacion").toString());
                Date fechaModificacion = new SimpleDateFormat("yyyy-MM-d hh:m:s").parse(json.get("fechaModificacion").toString());

                monederoModel = new MonederoModel(idMonedero, propietario, saldo, fechaCreacion, fechaModificacion);

                Mockito.when(msMock.getMonedero("2")).thenReturn(monederoModel);
                Mockito.when(msMock.getMonedero("3")).thenReturn(null);
            }
        } catch (FileNotFoundException | JSONException | ParseException e) {
            e.printStackTrace();
            Mockito.doThrow(e);
        }
    }

    @Test
    void getMonederoById_2() {
        MonederoModel monederoModel;
        MonederoController monederoController = new MonederoController();

        ReflectionTestUtils.setField(monederoController, "monederoService", msMock);

        monederoModel = monederoController.getMonedero("2");

        Assertions.assertEquals("2", monederoModel.getIdMonedero());
        Assertions.assertEquals("Laura Lopez", monederoModel.getPropietaro());
        Assertions.assertEquals(575000, monederoModel.getSaldo());
        try {
            Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-d H:m:s").parse("2021-01-12 07:22:36"), monederoModel.getFechaCreacion());
            Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-d H:m:s").parse("2021-09-14 13:22:36"), monederoModel.getFechaModificacion());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(575000, monederoModel.getSaldo());
        Assertions.assertEquals(575000, monederoModel.getSaldo());
    }

    @Test
    void getMonederoById_3() {
        MonederoModel monederoModel;
        MonederoController monederoController = new MonederoController();
        ReflectionTestUtils.setField(monederoController, "monederoService", msMock);
        monederoModel = monederoController.getMonedero("3");
        Assertions.assertNull(monederoModel);
    }
}