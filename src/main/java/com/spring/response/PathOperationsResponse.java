package com.spring.response;

import java.util.List;

public class PathOperationsResponse extends GraphOperationsResponse {
    private List<Integer> shortestWay;


    public PathOperationsResponse(final int id, final List<Integer> shortestWay) {
        super(id);

        this.shortestWay = shortestWay;
    }

    public List<Integer> getShortestWay() {
        return shortestWay;
    }

    public void setShortestWay(final List<Integer> shortestWay) {
        this.shortestWay = shortestWay;
    }
}
