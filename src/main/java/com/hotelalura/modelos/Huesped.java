package com.hotelalura.modelos;

public class Huesped {
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer idReserva;
    private Integer id;
    public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }
    public Huesped(Integer id,String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public Integer getId() {
        return id;
    }
}
