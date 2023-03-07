package org.example.modelo;

import java.util.LinkedList;

public class RutaCorta {
    private LinkedList<String> path;
    private double totalCost;

    public RutaCorta(LinkedList<String> path, double totalCost) {
        this.path = path;
        this.totalCost = totalCost;
    }

    public LinkedList<String> getPath() {
        return path;
    }

    public double getTotalCost() {
        return totalCost;
    }
}