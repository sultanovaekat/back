package com.example.demo2;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Queue;


public class Interconnector implements Interconnectable {

    @Resource(mappedName = "jms/TestFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/TestQueue")
    private Queue queue;

    @Override
    public void info() {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            producer.send(queue,"Ассортимент обновился");
        } catch (Exception e) {}
    }
    // выходной адаптер(то что должно послать сообщение в очеред сообщений, клиент 1 на схеме)
}