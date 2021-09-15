package com.davivienda.monedero.service;

import com.davivienda.monedero.model.MonederoModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonederoService {

    public MonederoModel getMonedero(String idMonedero) {
        return searchMonedero(idMonedero);
    }

    private MonederoModel searchMonedero(String idMonedero) {

        MonederoModel monederoModel = new MonederoModel(idMonedero, "Carlos Díaz", 100000, new Date(), new Date());
        return monederoModel;
    }
}