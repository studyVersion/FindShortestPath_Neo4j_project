package org.example.dao;

import org.example.modelo.Ciudad;
import org.example.modelo.RutaCorta;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

import java.util.LinkedList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class CiudadDao {
    String uri = "bolt://192.168.1.136:7687";
    //String uri = "bolt://localhost:7687";
    String user = "neo4j";
    String password = "12345678";
    Driver driver;
    Session session;

    public CiudadDao() {

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

    public void create(Ciudad ciudad) {
        start();
        String query = "CREATE (n:City {name: $nombre})";
        session.run(query, parameters("nombre", ciudad.getNombre()));
        exit();
    }

    public Ciudad read(String nombre) {
        start();
        Ciudad city = null;

        String query = "MATCH (c:City {name: $nombre}) RETURN c";
        Result result = session.run(query, parameters("nombre", nombre));
        if (result.hasNext()) {
            Record record = result.next();
            Node node = record.get("c").asNode();
            String name = node.get("name").asString();
            city = new Ciudad(name);
        }
        exit();
        return city;
    }



}
