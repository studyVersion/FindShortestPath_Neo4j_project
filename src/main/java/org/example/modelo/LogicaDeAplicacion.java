package org.example.modelo;

import org.example.factory.Factory;

import java.util.LinkedList;


public class LogicaDeAplicacion {

    Factory factory = new Factory();

    public Ciudad encontrarCiudad(String nombre) {

        return factory.ciudadDao().read(nombre);
    }

    public short insertCiudad(String nombre) {
        short value = 0;
        if (encontrarCiudad(nombre) == null) {

            factory.ciudadDao().create(new Ciudad(nombre));

        } else {
            value = -1;
        }
        return value;

    }

    public Relacion encontrarRelacion(String cuidad1, String ciudad2, int km){
        Relacion relacion = null;
        if (encontrarCiudad(cuidad1) != null && encontrarCiudad(ciudad2) != null) {
            Ciudad uno = encontrarCiudad(cuidad1);
            Ciudad dos = encontrarCiudad(ciudad2);
            relacion = factory.relacionDao().read(new Relacion(uno, dos, km));
        }
        return relacion;
    }
    public short EstablecerConexion(String cuidad1, String ciudad2, int km) {
        Relacion relacion = null;
        Ciudad uno = null;
        Ciudad dos = null;
        short value = 0;

        if (encontrarCiudad(cuidad1) != null && encontrarCiudad(ciudad2) != null) {
            uno = encontrarCiudad(cuidad1);
            dos = encontrarCiudad(ciudad2);
            relacion = new Relacion(uno, dos, km);
            // crear relacion
            if (factory.relacionDao().read(relacion) == null) {
                factory.relacionDao().create(relacion);

            }else {
                value = 2;
            }
        }else{
            value = 1;
        }

        return value;
    }

    public short modificarConexion(String c1, String c2, int km) {
        Relacion relacion= null;
        short value = -1;
        if (encontrarRelacion(c1, c2, km) != null){
            relacion = encontrarRelacion(c1, c2, km);
            factory.relacionDao().update(relacion);
            value = 0;
        }
        return value;
    }


    public String printRuta(LinkedList<String> list) {
        StringBuilder sb = new StringBuilder();

        for (String s : list) {
            sb.append(s).append(" --> ");
        }
        int arrowLength = " --> ".length();
        if (sb.length() >= arrowLength) {
            sb.delete(sb.length() - arrowLength, sb.length());
        }
        return sb.toString();
    }

    public String shortRout(String ciudad1, String ciudad2) {
        String value = "Ruta optima: ";
        double distancia = 0;
        RutaCorta ruta = factory.rutaCortaDao().read(ciudad1, ciudad2);
        value += printRuta(ruta.getPath());
        distancia = ruta.getTotalCost();
        value += "\n\nDistancia: " + distancia + " km";

        return value;
    }
}


