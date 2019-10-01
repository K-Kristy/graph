package com.spring.request;

public class PathOperationsRequest extends GraphOperationsRequest {
    private int startVertex;


    public PathOperationsRequest(final int id, final int startVertex) {
        super(id);

        this.startVertex = startVertex;
    }

    public PathOperationsRequest(){
    }


    public int getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(final int startVertex) {
        this.startVertex = startVertex;
    }

    @Override
    public String toString(){
        return super.toString() +
                " startVertex : " + startVertex;
    }
}
