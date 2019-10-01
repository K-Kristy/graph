package com.spring.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.spring.request.ConnectednessOperationsRequest;
import com.spring.request.PathOperationsRequest;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConnectednessOperationsResponse.class, name = "ConnectednessOperationsResponse"),
        @JsonSubTypes.Type(value = PathOperationsResponse.class, name = "PathOperationsResponse")
})
public abstract class GraphOperationsResponse {

    private int id;

    protected GraphOperationsResponse(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
