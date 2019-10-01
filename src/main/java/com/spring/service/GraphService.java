package com.spring.service;

import com.spring.entity.Graph;
import com.spring.request.GraphOperationsRequest;
import com.spring.response.GraphOperationsResponse;

import java.util.concurrent.ExecutionException;

public interface GraphService {

    int createAndSaveGraph(int vertexAmount);
    Graph getGraphById(final int id);
    GraphOperationsResponse operateGraph(GraphOperationsRequest request) throws ExecutionException, InterruptedException;
}
