package com.hotelalura.modelos;

public class Reserva {
    private String fechaE;
    private String fechaS;
    private Integer valor;
    private String formaPago;
    private Integer id;

    public Reserva(String fechaE, String fechaS, Integer valor, String formaPago) {
        this.fechaE = fechaE;
        this.fechaS = fechaS;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    public Reserva(Integer id, String fechaE, String fechaS, Integer valor, String formaPago) {
        this.id = id;
        this.fechaE = fechaE;
        this.fechaS = fechaS;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    public String getFechaE() {
        return fechaE;
    }

    public String getFechaS() {
        return fechaS;
    }

    public Integer getValor() {
        return valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
