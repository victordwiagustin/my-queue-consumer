package com.javmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javmq.client.RestClient;
import com.javmq.model.SampleModel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by neviim 2 on 16/11/2017.
 */
@Component
public class MyMessageListener implements MessageListener {

    private static final Logger log = Logger.getLogger(MyMessageListener.class);

    @Autowired
    RestClient restClient;

    @Override
    public void onMessage(Message message) {
        byte[] byteBody = message.getBody();
        String msgStr = new String(byteBody);

//        Map<String, Object> msgMap = new HashMap<>();
        HashMap<String, Object> msgMap = null;
        try {
            msgMap = new ObjectMapper().readValue(msgStr, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String postUrl = msgMap.get("url").toString();

        log.info("Received <" + msgStr + ">");
        log.info("Message <" + message + ">");
        log.info("Message processed...");

        SampleModel sampleModel= new SampleModel(123, msgStr, 23);
//        String postUrl = "http://localhost:8088/";
        restClient.postEntity(msgMap, postUrl);

        log.info("Success!");
    }

}
