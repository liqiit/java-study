package com.mongodb.config;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.listener.DocumnetMessageListener;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Title: MongoConfig
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2021/1/4
 */
@Configuration
public class MongoConfig {
    @Bean
    MessageListenerContainer messageListenerContainer(MongoTemplate template, DocumnetMessageListener documnetMessageListener) {
        Executor executor = Executors.newSingleThreadExecutor();
        MessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer(template, executor) {
            @Override
            public boolean isAutoStartup() {
                return true;
            }
        };

        ChangeStreamRequest<Document> request = ChangeStreamRequest.builder(documnetMessageListener)
                .collection("items")  //需要监听的集合名，不指定默认监听数据库的
                .filter(
                        newAggregation(
                                match(where("operationType").in("insert", "update", "replace")),
                                match(where("score").lte(0))
                        )
                ) //过滤需要监听的操作类型，可以根据需求指定过滤条件
                .fullDocumentLookup(FullDocument.UPDATE_LOOKUP) //不设置时，文档更新时，只会发送变更字段的信息，设置UPDATE_LOOKUP会返回文档的全部信息
                .build();
        messageListenerContainer.register(request, Document.class);

        return messageListenerContainer;
    }
}
