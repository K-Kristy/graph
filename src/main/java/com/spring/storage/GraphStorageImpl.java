package com.spring.storage;

import com.spring.entity.Graph;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class GraphStorageImpl implements GraphStorage {

    private final ConcurrentMap<Integer, Graph> graphs = new ConcurrentHashMap<>();

    @Override
    public void saveGraph(Graph graph) {
        graphs.put(graph.getId(), graph);
    }

    @Override
    @Nullable
    public Graph getGraphById(final int id) {
        return graphs.get(id);
    }

    @Override
    public void cleanStorage() {
        graphs.clear();
    }

}
