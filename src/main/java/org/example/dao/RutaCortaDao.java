package org.example.dao;

import org.example.modelo.RutaCorta;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.LinkedList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class RutaCortaDao {

        String uri = "bolt://192.168.1.136:7687";
        //String uri = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "12345678";
        Driver driver;
        Session session;

        public RutaCortaDao() {

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



        public RutaCorta read(String ciudad1, String ciudad2){
            start();

            // crear o actualizar un graph
            actualizarMyGraph();

            // Definir la consulta Cypher
            String query = "MATCH (start:City {name: $ciudad1}), (end:City {name: $ciudad2}) " +
                            "CALL gds.shortestPath.dijkstra.stream('myGraph', { " +
                            "  sourceNode: start, " +
                            "  targetNode: end, " +
                            "  relationshipWeightProperty: 'km' }) " +
                            "YIELD index, nodeIds, totalCost " +
                            "RETURN [nodeId IN nodeIds | gds.util.asNode(nodeId).name ] AS nodeNames, totalCost " +
                            "LIMIT 1;";

            // Ejecutar la consulta y procesar los resultados
            Result result = session.run(query, parameters("ciudad1", ciudad1, "ciudad2", ciudad2));
            LinkedList<String> path = new LinkedList<>();
            double cost = -1; // Initialize cost to -1
            while (result.hasNext()) {
                Record record = result.next();
                List<Object> nodeNames = record.get("nodeNames").asList();
                for (Object nodeName : nodeNames) {
                    path.addLast((String) nodeName);
                }
                Value totalCostValue = record.get("totalCost");
                if (!totalCostValue.isNull()) {
                    cost = totalCostValue.asDouble(); // Obtener la distancia total a partir del resultado
                }
            }
            exit();


            return new RutaCorta(path, cost);
        }

    public void actualizarMyGraph() {

        try (Session session = driver.session()) {
            // Comprobar si myGraph existe
            String checkQuery = "CALL gds.graph.exists('myGraph') YIELD exists";
            Result checkResult = session.run(checkQuery);
            boolean graphExists = checkResult.next().get("exists").asBoolean();

            if (graphExists) {
                // Si myGraph existe, elimínalo y vuelve a crearlo
                String dropQuery = "CALL gds.graph.drop('myGraph') YIELD graphName";
                session.run(dropQuery);

                String createQuery = "CALL gds.graph.project('myGraph', 'City', { CONECTA: {  type: 'CONECTA', properties: 'km' }})";
                session.run(createQuery);
            } else {
                // Si myGraph no existe, créalo
                String createQuery = "CALL gds.graph.project('myGraph', 'City', { CONECTA: {  type: 'CONECTA', properties: 'km' }})";
                session.run(createQuery);
            }

        }
    }

    }
