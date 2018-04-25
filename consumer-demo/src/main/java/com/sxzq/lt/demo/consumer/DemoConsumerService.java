package com.sxzq.lt.demo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.sxzq.lt.demo.producer.UserInfo;
import com.sxzq.lt.rocketmq.common.MessageData;
import com.sxzq.lt.rocketmq.consumer.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lt on 2018/4/24.
 */
public class DemoConsumerService implements ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoConsumerService.class);

    @Override
    public boolean consume(Message message) {
        boolean consumeResult = false;
        LOGGER.info("begin consume demo service message");


        String msgId = "";
        if(message instanceof MessageExt){
            MessageExt messageExt =  (MessageExt)message;
            msgId = messageExt.getMsgId();
        }
        LOGGER.info("MsgId is:" + msgId + ", Consume message body is: {}", new String(message.getBody()));

        try {

            MessageData<UserInfo> messageData = JSONObject.parseObject(new String(message.getBody()),
                    new TypeReference<MessageData<UserInfo>>(){});

            if (null == messageData){
                LOGGER.error("mq message data is null");
                return consumeResult;
            }

            //TODO 根据UUID幂等控制
            String uuid = messageData.getUuid();

            UserInfo userInfo = messageData.getData();

            //业务处理...
            System.out.println(userInfo.getId());
            System.out.println(userInfo.getUsername());
            System.out.println(userInfo.getSex());


            consumeResult = true;
            //throw new Exception("Test producer retry times");
        } catch (Exception e) {
            LOGGER.error("demoConsumeService mq run failed: {}", e);
            consumeResult =  false;
        }


        LOGGER.info("end consume demo service message");
        return consumeResult;
    }
}
