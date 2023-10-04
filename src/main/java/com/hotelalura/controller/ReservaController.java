package com.hotelalura.controller;

import com.hotelalura.dao.ReservaDAO;
import com.hotelalura.modelos.Reserva;


public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public boolean reservar(Reserva reserva){
        return reservaDAO.generar(reserva);
    }
    public void eliminarUltimoReserva(Integer id){
        reservaDAO.eliminar(id);
    }
}
