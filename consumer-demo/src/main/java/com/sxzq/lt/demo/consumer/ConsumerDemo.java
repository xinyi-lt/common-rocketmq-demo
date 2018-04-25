package com.sxzq.lt.demo.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * consumer demo
 *
 */
public class ConsumerDemo {
    public static void main( String[] args ){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
