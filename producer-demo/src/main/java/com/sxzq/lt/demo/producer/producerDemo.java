package com.sxzq.lt.demo.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.sxzq.lt.rocketmq.common.MessageData;
import com.sxzq.lt.rocketmq.producer.RocketMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * producer Demo
 *
 */
public class producerDemo{

    private static Logger logger = LoggerFactory.getLogger(producerDemo.class);

    public static void main( String[] args ){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        RocketMQProducer producer = applicationContext.getBean(RocketMQProducer.class);

        //消息传输DTO
        MessageData<UserInfo> messageData = new MessageData<>();

        //时间戳
        messageData.setTimestamp(System.currentTimeMillis());
        //幂等控制根据UUID来控制
        messageData.setUuid(UUID.randomUUID().toString());

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername("C罗");
        userInfo.setSex("男");
        userInfo.setBirthday(new Date());

        messageData.setData(userInfo);

        logger.info("start to send message data:{}", JSON.toJSONString(messageData));
        //发送消息
        SendResult result = producer.sendMessage(
                new Message(MQConstant.MQ_TOPIC_PRODUCER_DEMO,
                MQConstant.MQ_TAG_PRODUCER_DEMO_FIRST,
                UUID.randomUUID().toString(),
                JSON.toJSONString(messageData).getBytes(Charset.forName("utf-8"))));

        logger.info("send message complete result:{} ", result);

    }
}
