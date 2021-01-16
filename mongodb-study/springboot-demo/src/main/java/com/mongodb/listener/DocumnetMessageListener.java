package com.mongodb.listener;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Title: DocumnetMessageListener
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2021/1/4
 */
@Component
@Slf4j
public class DocumnetMessageListener implements MessageListener<ChangeStreamDocument<Document>, Document> {

    @Override
    public void onMessage(Message<ChangeStreamDocument<Document>, Document> message) {
        message.getBody().get("imsi");
        log.info("Received Message in collection: {},message raw: {}, message body:{}",
                message.getProperties().getCollectionName(), message.getRaw(), message.getBody());

    }
}