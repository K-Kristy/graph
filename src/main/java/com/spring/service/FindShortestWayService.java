package com.spring.service;

import com.spring.entity.Graph;
import com.spring.request.PathOperationsRequest;
import com.spring.response.PathOperationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class FindShortestWayService implements GraphOperationService<PathOperationsRequest, PathOperationsResponse> {

    private static final int INF = Integer.MAX_VALUE;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    @Nullable
    public PathOperationsResponse execute(PathOperationsRequest request) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        long end = start + (request.getMills() == 0 ? Integer.MAX_VALUE : request.getMills());
        Thread.sleep(5);
        Future<PathOperationsResponse> result = threadPoolTaskExecutor.submit(() -> findShortestWay(request));

        boolean isTimeout = false;
        while (true){
            if(System.currentTimeMillis() >= end){
                result.cancel(true);
                break;
            }

            if(threadPoolTaskExecutor.getActiveCount() == 0){
                break;
            }
        }

        if(!result.isCancelled()){
            return result.get();
        }

        return null;
    }

    private PathOperationsResponse findShortestWay(final PathOperationsRequest request) {
        Graph graph = request.getGraph();
        int vertexAmount = graph.getVertexAmount();
        int[][] connections = Arrays.copyOf(graph.getConnections(), vertexAmount);
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                if(i != j && connections[i][j] == 0){
                    connections[i][j] = INF;
                }
            }
        }

        Integer[] minWays = new Integer[vertexAmount];

        for (int i = 0; i < vertexAmount; i++) {
            minWays[i] = INF;
        }

        minWays[request.getStartVertex()] = 0;

        boolean[] visitedVertex = new boolean[vertexAmount];
        while (true) {
            int min = -1;
            for (int i = 0; i < vertexAmount; i++) {
                if (!visitedVertex[i] && minWays[i] < INF && (min == -1 || minWays[min] > minWays[i])) {
                    min = i;
                }
            }

            if(min == -1) {
                break;
            }

            visitedVertex[min] = true;

            for (int i = 0; i < vertexAmount; i++) {
                if(!visitedVertex[i] && connections[min][i] < INF){
                    minWays[i] = min(minWays[i], minWays[min] + connections[min][i]);
                }
            }
        }

        return new PathOperationsResponse(graph.getId(), Arrays.asList(minWays));
    }

    @Override
    public Class getRequestClass() {
        return PathOperationsRequest.class;
    }

    private int min(final int vertexWay1, final int vertexWay2) {
        return vertexWay1 <= vertexWay2 ? vertexWay1 : vertexWay2;
    }

}
