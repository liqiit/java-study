package com.liqi.mongodb.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Title: CommonConnection
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/23
 */
public class CommonConnection {
    private static final String standardUrl = "mongodb://MogoDbUser:%40Mgxd!2i3rx6d5YI@192.168.1.182:27017,192.168.1.183:27017,192.168.1.184:27017/?authSource=mogos_db&readPreference=primaryPreferred&appname=MongoDB%20Compass&ssl=false";
    private static final String repliaSetUrl = "mongodb://host1:27017,host2:27017,host3:27017";

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(standardUrl);

        //mongoClient.listDatabaseNames().forEach(s -> System.out.println(s));
        MongoCollection mongoCollection = mongoClient.getDatabase("mogos_db").getCollection("items");
        List<Bson> pipeline = Collections.singletonList(
                Aggregates.match(
                        Filters.and(
                                Filters.eq("fullDocument.score", 0),
                                Filters.in("operationType", Arrays.asList("insert", "update", "delete","replace"))
                        )


                )
        );


        MongoCursor<ChangeStreamDocument<Document>> cursor = mongoCollection.watch(pipeline).iterator();
        //mongoCollection.updateOne(Filters.eq("imsi", "123345"), new Document("$set",new Document("score",0)));

        while (cursor.hasNext()) {
            ChangeStreamDocument<Document> next = cursor.next();
            String Operation = next.getOperationType().getValue();
            String tableName = next.getNamespace().getCollectionName();
            String imsi = (String) next.getFullDocument().get("imsi");
            System.out.println("imsi：" + imsi + "剩余流量已经为0");
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
