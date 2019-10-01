package com.spring.entity;

import java.util.List;

public class GraphInfo {
    private Graph graph;
    private List<Integer> shortestWay;
    private int connectednessAmount;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(final Graph graph) {
        this.graph = graph;
    }

    public List<Integer> getShortestWay() {
        return shortestWay;
    }

    public void setShortestWay(final List<Integer> shortestWay) {
        this.shortestWay = shortestWay;
    }

    public int getConnectednessAmount() {
        return connectednessAmount;
    }

    public void setConnectednessAmount(final int connectednessAmount) {
        this.connectednessAmount = connectednessAmount;
    }
}
