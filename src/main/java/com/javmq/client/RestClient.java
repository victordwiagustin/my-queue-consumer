package com.javmq.client;

import com.javmq.model.SampleModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by neviim 2 on 19/11/2017.
 */
@Component
public class RestClient {

    RestTemplate restTemplate;

    public RestClient(){
        restTemplate = new RestTemplate();
    }

//    public void postEntity(SampleModel sampleModel){
//        System.out.println("Begin /POST request!");
//        // replace http://localhost:8080 by your restful services
//        String postUrl = "http://localhost:8088/";
//
//        ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, sampleModel, String.class);
//        System.out.println("Response for Post Request: " + postResponse.getBody());
//    }

    public void postEntity(Object object, String postUrl){
        System.out.println("Begin /POST request!");
        // replace http://localhost:8080 by your restful services

        ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, object, String.class);
        System.out.println("Response for Post Request: " + postResponse.getBody());
    }

}
