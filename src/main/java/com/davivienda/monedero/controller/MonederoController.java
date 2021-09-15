package com.davivienda.monedero.controller;

import com.davivienda.monedero.model.MonederoModel;
import com.davivienda.monedero.service.MonederoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/monedero")
public class MonederoController {
    @Autowired
    MonederoService monederoService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonederoModel getMonedero(@RequestParam(name = "idMonedero") String idMonedero) {
        return monederoService.getMonedero(idMonedero);
    }
}
/*
Prueba
        1. Crear un API que permita la consulta de un monedero electrónico registrado en una clase tipo mock con nodejs o java
        2. Generar un repositorio con el código fuente en gitlab, github o el que maneje.
        3. Generar unidades de test para validar la consulta del monedero

        Nota: Se debe enviar la url al repositorio y se analizará durante la entrevista.
*/