package com.spring.service;

import com.spring.request.GraphOperationsRequest;
import com.spring.response.GraphOperationsResponse;

import java.util.concurrent.ExecutionException;

public interface GraphOperationService <T extends GraphOperationsRequest, R extends GraphOperationsResponse>{

    R execute(T request) throws InterruptedException, ExecutionException;
    Class getRequestClass();
}
