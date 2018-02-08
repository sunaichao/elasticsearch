package com.chit.create;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by Administrator on 2018/2/8.
 */
public class DocUpdate {
    public void docUpdate(String indexName, String typeName, String id) throws Exception{
        //ElasticSearch集群如果没有设置集群名称的话，就不需要setting了。如果设置了需要设置setting。
        Settings settings = Settings.settingsBuilder().put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));

        IndexRequest indexRequest = new IndexRequest(indexName,typeName,"3")
                .source(jsonBuilder()
                        .startObject()
                        .field("type",typeName)
                        .field("eventCount",2)
                        .field("eventDate",new Date())
                        .field("message","secilog insert doc test")
                        .endObject());

        UpdateRequest request = new UpdateRequest(indexName,typeName,"3")
                .doc(jsonBuilder().startObject().field("type","file").endObject())
                .upsert(indexRequest);
        client.update(request).get();
        client.close();
    }
}
