package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelos.Reserva;
import com.hotelalura.modelos.Usuario;

import java.sql.*;

public class ReservaDAO {
    public boolean generar(Reserva reserva) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("insert into reserva (fechaEntrada,fechaSalida,valor,formaPago) values (?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
            try(statement){
                statement.setString(1,reserva.getFechaE());
                statement.setString(2,reserva.getFechaS());
                statement.setInt(3,reserva.getValor());
                statement.setString(4,reserva.getFormaPago());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet){
                    if (resultSet.next()){
                        reserva.setId(resultSet.getInt(1));
                        return true;
                    }
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public void eliminar(Integer id) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("delete from reserva where idReserva=?");
            try(statement){
                statement.setInt(1,id);
                statement.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
