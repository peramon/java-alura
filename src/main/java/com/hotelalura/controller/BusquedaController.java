package com.hotelalura.controller;

import com.hotelalura.dao.BusquedaDao;
import com.hotelalura.modelos.Huesped;
import com.hotelalura.modelos.Reserva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusquedaController {
    private BusquedaDao busquedaDao;

    public BusquedaController() {
        busquedaDao = new BusquedaDao();
    }

    public List<Reserva> listarReservas() {
        return busquedaDao.listarReservas();
    }
    public List<Huesped> listarHuespedes() {
        return busquedaDao.listarHuespedes();
    }
    public void eliminarPorReserva(Integer idReserva) {
        busquedaDao.eliminarPorReserva(idReserva);
    }

    public void modificarReserva(Reserva reserva) {
        busquedaDao.modificarReserva(reserva);
    }

    public void modificarHuesped(Huesped huesped) {
        busquedaDao.modificarHuesped(huesped);
    }

    public List<List> buscarPorPalabraClave(String text) {
        return busquedaDao.buscarPorPalabraClave(text);
    }
}
