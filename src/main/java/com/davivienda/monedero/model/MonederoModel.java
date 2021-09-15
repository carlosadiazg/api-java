package com.davivienda.monedero.model;

import java.util.Date;

public class MonederoModel {

    private String idMonedero;
    private String propietaro;
    private float saldo;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public MonederoModel(){}

    public MonederoModel(String idMonedero, String propietaro, float saldo, Date fechaCreacion, Date fechaModificacion) {
        this.idMonedero = idMonedero;
        this.propietaro = propietaro;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getIdMonedero() {
        return idMonedero;
    }

    public String getPropietaro() {
        return propietaro;
    }

    public float getSaldo() {
        return saldo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }
}