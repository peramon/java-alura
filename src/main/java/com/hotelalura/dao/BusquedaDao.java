package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelos.Huesped;
import com.hotelalura.modelos.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusquedaDao {
    public List<Reserva> listarReservas() {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        List<Reserva> resultado = new ArrayList<>();
        try (con){
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM reserva");
            try(statement){
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    //String fechaE, String fechaS, Integer valor, String formaPago
                    while (resultSet.next()){
                        Reserva fila = new Reserva(resultSet.getString("FECHAENTRADA"),
                                resultSet.getString("FECHASALIDA"),
                                resultSet.getInt("VALOR"),
                                resultSet.getString("FORMAPAGO"));
                        fila.setId(resultSet.getInt("IDRESERVA"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void eliminarPorReserva(Integer idReserva) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("DELETE huesped, reserva " +
                    "FROM huesped " +
                    "JOIN reserva ON huesped.idReservaF = reserva.idReserva " +
                    "WHERE idReserva=?");
            try(statement){
                statement.setInt(1,idReserva);
                statement.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void modificarReserva(Reserva reserva) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("UPDATE reserva SET fechaEntrada=? , fechaSalida=? , valor=? , formaPago=? WHERE idReserva=?");
            try(statement){
                statement.setString(1, reserva.getFechaE());
                statement.setString(2, reserva.getFechaS());
                statement.setInt(3,reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
                statement.setInt(5,reserva.getId());
                statement.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listarHuespedes() {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        List<Huesped> resultado = new ArrayList<>();
        try (con){
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM huesped");
            try(statement){
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    //Integer id,String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva
                    while (resultSet.next()){
                        Huesped fila = new Huesped(resultSet.getInt("idHuesped"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("fechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("idReservaF"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void modificarHuesped(Huesped huesped) {
        final Connection con =  new ConnectionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement("UPDATE huesped SET nombre=?, apellido=?, fechaNacimiento=?,  nacionalidad=? ,telefono=? ,idReservaF=? WHERE idhuesped=?");
            try(statement){
                statement.setString(1,huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.setInt(7,huesped.getId());

                statement.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Integer tomarNumeros(String palabraConNumeros) {
        StringBuilder numbersOnly = new StringBuilder();
        Integer numeroEncontrado;

        for (char c : palabraConNumeros.toCharArray()) {
            if (Character.isDigit(c)) {
                numbersOnly.append(c);
            }
        }

        try {
            numeroEncontrado = Integer.parseInt(String.valueOf(numbersOnly));
        }catch (NumberFormatException e){
            return 0;
        }
        return numeroEncontrado;
    }

    public List<List> buscarPorPalabraClave(String text) {
        final Connection con =  new ConnectionFactory().recuperaConexion();

        List<List> resultado= new ArrayList<>();
        List<Huesped> huespedes = new ArrayList<>();
        List<Reserva> reservas= new ArrayList<>();
        try (con){
            final PreparedStatement statement = con.prepareStatement("SELECT * " +
                    "FROM huesped " +
                    "JOIN reserva " +
                    "ON huesped.idReservaF = reserva.idReserva " +
                    "Where nombre=? or apellido=? or fechaNacimiento=? or  nacionalidad=? or telefono=? or fechaEntrada=? or fechaSalida=? or formaPago=? or idhuesped=? or idReservaF=?");
            try(statement){
                statement.setString(1,text);
                statement.setString(2, text);
                statement.setString(3, text);
                statement.setString(4, text);
                statement.setString(5, text);
                statement.setString(6, text);
                statement.setString(7, text);
                statement.setString(8, text);
                statement.setInt(9, tomarNumeros(text));
                statement.setInt(10,tomarNumeros(text));
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    //Integer id,String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva
                    while (resultSet.next()){
                        Huesped fila = new Huesped(resultSet.getInt("idHuesped"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("fechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("idReservaF"));
                        huespedes.add(fila);
                        Reserva fila1 = new Reserva(resultSet.getString("FECHAENTRADA"),
                                resultSet.getString("FECHASALIDA"),
                                resultSet.getInt("VALOR"),
                                resultSet.getString("FORMAPAGO"));
                        fila1.setId(resultSet.getInt("IDRESERVA"));
                        reservas.add(fila1);
                    }
                    resultado.add(huespedes);
                    resultado.add(reservas);
                }
            }
            return resultado;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
