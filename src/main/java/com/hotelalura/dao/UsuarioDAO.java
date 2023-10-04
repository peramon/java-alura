package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validar(Usuario usuario) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("SELECT CORREO, CONTRASEÑA FROM USUARIO WHERE CORREO =?");
            try(statement){
                statement.setString(1,usuario.getCorreo());
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    if(!resultSet.next()){
                        return false;
                    }
                    Usuario auxUsuario= new Usuario(resultSet.getString("CORREO"),resultSet.getString("CONTRASEÑA"));
                    return auxUsuario.equals(usuario);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
