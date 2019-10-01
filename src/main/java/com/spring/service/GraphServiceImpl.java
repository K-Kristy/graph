package com.spring.service;

import com.spring.entity.Graph;
import com.spring.request.GraphOperationsRequest;
import com.spring.response.GraphOperationsResponse;
import com.spring.storage.GraphStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class GraphServiceImpl implements GraphService {

    private final Logger logger = LoggerFactory.getLogger(GraphServiceImpl.class);

    private Map<Class, GraphOperationService> services = new HashMap<>();

    @Autowired
    private List<GraphOperationService> operationServices;

    @PostConstruct
    private void init() {
        for (final GraphOperationService operationService : operationServices) {
            services.put(operationService.getRequestClass(), operationService);
        }
    }

    @Autowired
    private GraphStorage graphStorage;

    @Override
    public int createAndSaveGraph(int vertexAmount) {
        Graph graph = createGraph(vertexAmount);
        graphStorage.saveGraph(graph);

        return graph.getId();
    }

    @Override
    @Nullable
    public Graph getGraphById(final int id) {
        return graphStorage.getGraphById(id);
    }

    @Override
    public GraphOperationsResponse operateGraph(final GraphOperationsRequest request) throws ExecutionException, InterruptedException {
        final Graph graph = getGraphById(request.getId());
        if(graph == null){
            return null;
        }

        request.setGraph(graph);
        return services.get(request.getClass()).execute(request);
    }

    private Graph createGraph(final int vertexAmount) {
        while (true) {
            try {
                return new Graph(vertexAmount);
            } catch (OutOfMemoryError error) {
                logger.error("Not enough memory!");
                graphStorage.cleanStorage();
            }
        }
    }

}
