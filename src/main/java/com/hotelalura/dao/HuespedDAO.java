package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelos.Huesped;
import com.hotelalura.modelos.Reserva;

import java.sql.*;

public class HuespedDAO {
    public boolean generar(Huesped huesped) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("insert into huesped (nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReservaF) values (?,?,?,?,?,?);");
            try(statement){
                statement.setString(1, huesped.getNombre());
                statement.setString(2,huesped.getApellido());
                statement.setString(3,huesped.getFechaNacimiento());
                statement.setString(4,huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.execute();
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
