package org.example.factory;

import org.example.dao.CiudadDao;
import org.example.dao.RelacionDao;
import org.example.dao.RutaCortaDao;
import org.example.modelo.Relacion;

public class Factory {

    public Factory() {
    }

    public CiudadDao ciudadDao(){
        CiudadDao ciudadDao = new CiudadDao();
        return ciudadDao;
    }

    public RelacionDao relacionDao(){
        RelacionDao relacionDao = new RelacionDao();
        return relacionDao;
    }

    public RutaCortaDao rutaCortaDao(){
        RutaCortaDao rutaCorta = new RutaCortaDao();
        return rutaCorta;
    }
}
