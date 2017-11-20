package com.javmq;

import com.javmq.listener.MyMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by neviim 2 on 16/11/2017.
 */

@SpringBootApplication
public class Application {

    public final static String SFG_MESSAGE_QUEUE = "spongebobs";

    @Bean
    Queue queue() {
        return new Queue(SFG_MESSAGE_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(SFG_MESSAGE_QUEUE);
    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_QUEUE);
//    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SFG_MESSAGE_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MyMessageListener listener) {
        return new MessageListenerAdapter(listener);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
