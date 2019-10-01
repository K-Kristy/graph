package com.spring.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.spring.entity.Graph;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConnectednessOperationsRequest.class, name = "ConnectednessOperationsRequest"),
        @JsonSubTypes.Type(value = PathOperationsRequest.class, name = "PathOperationsRequest")
})
public abstract class GraphOperationsRequest {

    private int id;
    private long mills;
    @JsonIgnore
    private Graph graph;

    protected GraphOperationsRequest(){}

    protected GraphOperationsRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(final Graph graph) {
        this.graph = graph;
    }

    public long getMills() {
        return mills;
    }

    @Override
    public String toString(){
        return "id : " + id;
    }
}
