package com.example.graphqMvclClient.service;

import com.example.graphqMvclClient.model.Course;
import com.example.graphqMvclClient.util.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String sayHello(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBasicAuth("admin","password");

        // request body parameters
        Map<String, Object> body = new HashMap<>();
        body.put("query", " { courses(page:2,size:1){\n" +
                "    startDate,\n" +
                "    description\n" +
                " } }");



        ResponseEntity<String> str=restTemplate.postForEntity(
                "http://localhost:9090/graphql"
                , new HttpEntity<>(body, httpHeaders),
                String.class
        );


        System.out.println(str.getBody());

        Map<String,Object> resBody=new Parser<Object>().getMap(str.getBody());
        String data="";
        String error="";
        if(resBody.containsKey("data")&&resBody.get("data")!=null)
            data=resBody.get("data").toString();
        if(resBody.containsKey("errors"))
            error=resBody.get("errors").toString();

        //does not work for graphql response, should organize deserialize annotations
        List<Course> list=new Parser<Course>().getListObject(data);
        list.forEach(i-> {
            System.out.println(i.getDescription());
        });

        System.out.println("data: "+data);
        System.out.println("error: "+error);
        return data;
    }
}
