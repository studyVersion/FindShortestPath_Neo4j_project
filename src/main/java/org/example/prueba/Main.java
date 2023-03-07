package org.example.prueba;

import org.example.modelo.LogicaDeAplicacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogicaDeAplicacion prueba = new LogicaDeAplicacion();


        while (true) {
            try {

                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println("|   1. Para Añadir ciudad                        |");
                System.out.println("|   2. Para Establecer conexión entre 2 ciudades |");
                System.out.println("|   3. Para Modificar conexión                   |");
                System.out.println("|   4. Para encontrar Ruta optima                |");
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");


                System.out.println("Elege una option: ");

                int option = Integer.valueOf(sc.nextLine());

                if (option == 1) {
                    System.out.println("Nombre Ciudad:");
                    String city = sc.nextLine();

                    short value = prueba.insertCiudad(city);
                    if (value < 0) {
                        System.out.printf("Ciudad existe.\n");
                    } else {
                        System.out.println("Ciudad registrada con exito.\n");
                    }
                }

                if (option == 2) {
                    System.out.println("Indica el nombre de una primera ciudad:");
                    String c1 = sc.nextLine();
                    System.out.println("Indica el nombre de una segunda ciudad:");
                    String c2 = sc.nextLine();
                    System.out.println("Indica la  distancia en Km:");
                    int km = Integer.valueOf(sc.nextLine());

                    short value = prueba.EstablecerConexion(c1, c2, km);

                    if (value == 0) {
                        System.out.println("Conexion hecha con exito\n");
                    } else if (value == 1) {
                        System.out.println("Error: Una o ambas ciudades no existen\n");
                    } else {
                        System.out.println("Error: Ya existe una conexión entre las dos ciudades\n");
                    }

                }
                if (option == 3) {
                    System.out.println("Indica el nombre de una primera ciudad:");
                    String c1 = sc.nextLine();
                    System.out.println("Indica el nombre de una segunda ciudad:");
                    String c2 = sc.nextLine();
                    System.out.println("Indica la  distancia en Km:");
                    int km = Integer.valueOf(sc.nextLine());

                    short value = prueba.modificarConexion(c1, c2, km);
                    if (value == -1) {
                        System.out.println("Conexión o ciudades no existentes.\n");
                    }else{
                        System.out.println("Modificacion hecha con exito.\n");
                    }

                }


                if (option == 4) {
                    System.out.println("Indica el nombre de una primera ciudad:");
                    String c1 = sc.nextLine();
                    System.out.println("Indica el nombre de una segunda ciudad:");
                    String c2 = sc.nextLine();
                    System.out.println("\n----------------------------------------------------");
                    System.out.println(prueba.shortRout(c1, c2));
                    System.out.println("----------------------------------------------------\n");


                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}