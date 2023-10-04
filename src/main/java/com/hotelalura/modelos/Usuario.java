package com.hotelalura.modelos;

public class Usuario{
    private String correo;
    private String contrasenia;
    public Usuario( String correo, String contrasenia){
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public boolean equals(Usuario usuario){
        return usuario.getContrasenia().equals(this.getContrasenia()) && usuario.getCorreo().equals(this.getCorreo()) ;
    }
}
