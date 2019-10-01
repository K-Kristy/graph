package com.spring.service;

import com.spring.entity.Graph;
import com.spring.request.ConnectednessOperationsRequest;
import com.spring.response.ConnectednessOperationsResponse;
import org.springframework.stereotype.Component;

@Component
public class FindConnectednessAmountService implements GraphOperationService<ConnectednessOperationsRequest, ConnectednessOperationsResponse>{

    private int[][] connections;
    private int vertexAmount;
    private boolean[] used;
    private int amount;


    @Override
    public ConnectednessOperationsResponse execute(ConnectednessOperationsRequest request) {
        Graph graph = request.getGraph();
        connections = graph.getConnections();
        vertexAmount = graph.getVertexAmount();
        used = new boolean[vertexAmount];

        for (int i = 0; i < vertexAmount; i++) {
            if (!used[i]) {
                amount++;

                go(i);
            }

        }

        return new ConnectednessOperationsResponse(graph.getId(), vertexAmount);
    }

    @Override
    public Class getRequestClass() {
        return ConnectednessOperationsRequest.class;
    }

    private void go(final int currentVertex) {
        used[currentVertex] = true;
        for (int j = 0; j < vertexAmount; j++) {
            if (!used[j] && connections[currentVertex][j] == 1) {
                go(j);
            }
        }
    }

}
