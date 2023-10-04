package com.hotelalura.controller;

import com.hotelalura.dao.UsuarioDAO;
import com.hotelalura.modelos.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean validarUsuario(Usuario usuario){
        return usuarioDAO.validar(usuario);
    }
}
