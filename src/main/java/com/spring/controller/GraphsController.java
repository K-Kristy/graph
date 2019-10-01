package com.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.request.GraphOperationsRequest;
import com.spring.service.GraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class GraphsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphsController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private GraphService graphService;


    @RequestMapping("/add")
    public int addNewGraph(@RequestParam(value = "vertexAmount") int vertexAmount) {
        return graphService.createAndSaveGraph(vertexAmount);
    }


    @RequestMapping("/operate")
    public String operateGraph(@RequestBody String json) throws IOException, ExecutionException, InterruptedException {
        GraphOperationsRequest request = objectMapper.readValue(json, GraphOperationsRequest.class);
        LOGGER.info(request.toString());

        return objectMapper.writeValueAsString(graphService.operateGraph(request));
    }

}
