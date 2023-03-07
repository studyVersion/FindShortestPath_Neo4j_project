package org.example.dao;

import org.example.modelo.Relacion;
import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

public class RelacionDao {

    String uri = "bolt://192.168.1.136:7687";
    //String uri = "bolt://localhost:7687";
    String user = "neo4j";
    String password = "12345678";
    Driver driver;
    Session session;

    public RelacionDao() {

    }

    public void start() {
        // Create a new driver instance
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        // Create a new session
        session = driver.session();
    }

    public void exit() {
        session.close();
        driver.close();
    }



   public void create(Relacion relacion){
       start();
       String query = "MATCH (c1:City {name: $ciudad1}), (c2:City {name: $ciudad2}) " +
               "CREATE (c1)-[:CONECTA {km: $km}]->(c2)" +
               "CREATE (c2)-[:CONECTA {km: $km}]->(c1)";
       session.run(query, parameters("ciudad1", relacion.getCiudadUno().getNombre(),
                                                  "ciudad2", relacion.getCiudadDos().getNombre(),
                                                  "km", relacion.getDistancia()));
       exit();

   }

    public Relacion read(Relacion relacion){
        start();
        // consulta para ver que hay relación
        String checkConnectionQuery = "MATCH (c1:City {name: $ciudad1})-[r:CONECTA]->(c2:City {name: $ciudad2}) RETURN r";

        Result result = session.run(checkConnectionQuery, parameters("ciudad1", relacion.getCiudadUno().getNombre(),
                                                                                  "ciudad2", relacion.getCiudadDos().getNombre()));

       // si la relación existe devuelve la mismo que le demos
        if (result.hasNext()){
            return relacion;
        }
        exit();
        // si la relacion no existe devuelve null
        return null;
    }

    public void update(Relacion relacion){
        start();

        String query = "MATCH (c1:City {name: $nombre1})-[r:CONECTA]-(c2:City {name: $nombre2}) SET r.km = $km";
        Result result = session.run(query, parameters("nombre1", relacion.getCiudadUno().getNombre(),
                                                                   "nombre2", relacion.getCiudadDos().getNombre(),
                                                                   "km", relacion.getDistancia()));

        exit();
    }

}
