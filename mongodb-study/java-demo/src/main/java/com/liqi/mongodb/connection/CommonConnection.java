package com.liqi.mongodb.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Title: CommonConnection
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/23
 */
public class CommonConnection {
    private static final String standardUrl = "mongodb://localhost:27017";
    private static final String repliaSetUrl = "mongodb://host1:27017,host2:27017,host3:27017";

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(standardUrl);

        mongoClient.listDatabaseNames().forEach(s -> System.out.println(s));
    }
}
