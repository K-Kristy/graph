package com.spring.response;

public class ConnectednessOperationsResponse extends GraphOperationsResponse {

    private int connectednessAmount;

    public ConnectednessOperationsResponse(final int id, final int connectednessAmount) {
        super(id);

        this.connectednessAmount = connectednessAmount;
    }

    public int getConnectednessAmount() {
        return connectednessAmount;
    }

    public void setConnectednessAmount(final int connectednessAmount) {
        this.connectednessAmount = connectednessAmount;
    }
}
