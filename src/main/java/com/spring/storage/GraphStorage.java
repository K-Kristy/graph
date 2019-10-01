package com.spring.storage;

import com.spring.entity.Graph;

public interface GraphStorage {

    void saveGraph(Graph graph);
    Graph getGraphById(int id);
    void cleanStorage();
}
