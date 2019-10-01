package com.spring.entity;

import java.util.Arrays;

public class Graph {
    private final int id;
    private final int vertexAmount;
    private final int[][] connections;

    public Graph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        this.connections = new int[vertexAmount][vertexAmount];

        for (int i = 0; i < vertexAmount - 1; i++) {
            for (int j = i + 1; j < vertexAmount; j++) {
                if (i != j) {
                    int randValue = Math.random() < 0.5 ? 0 : 1;
                    connections[i][j] = randValue;
                    connections[j][i] = randValue;
                }
            }
        }

        this.id = Arrays.deepHashCode(this.connections);
    }

    public int getId() {
        return id;
    }

    public int getVertexAmount() {
        return vertexAmount;
    }

    public int[][] getConnections() {
        return connections;
    }
}
