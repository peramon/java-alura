package com.hotelalura.controller;

import com.hotelalura.dao.HuespedDAO;
import com.hotelalura.modelos.Huesped;

public class HuespedController {
    HuespedDAO huespedDAO;
    public HuespedController(){
        huespedDAO = new HuespedDAO();
    }

    public boolean guardarHuesped(Huesped huesped){
        return huespedDAO.generar(huesped);
    }
}
