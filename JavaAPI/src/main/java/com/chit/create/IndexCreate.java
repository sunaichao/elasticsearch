package com.chit.create;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * 创建索引文档
 * Created by Administrator on 2018/2/6.
 */
public class IndexCreate {

    /**
     * 创建索引文档
     * @param indexName 索引名称
     * @param typeName  类型名称
     * @throws IOException
     */
    public void createIndex(String indexName, String typeName) throws IOException{
        //如果elasticsearch服务端没有设置集群名称 是默认的话。可以不用设置Setting，否则请设置setting
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));
//        Client client = TransportClient.builder().build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("settings")
                .field("number_of_shards",1)//设置分片数量
                .field("number_of_replicas",0)//设置副本数量
                .endObject()
                .endObject()
                .startObject()
                .startObject(typeName)//type名称
                .startObject("properties")
                .startObject("type")
                .field("type","string")
                .field("store","yes")
                .endObject()
                .startObject("eventCount")
                .field("type","long")
                .field("store","yes")
                .endObject()
                .startObject("eventDate")
                .field("type","date")
                .field("format","dateOptionalTime")
                .field("store","yes")
                .endObject()
                .startObject("message")
                .field("type","string")
                .field("index","not_analyzed")
                .field("store","yes")
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices()
                .prepareCreate(indexName)//index名称
                .setSource(mapping);
        CreateIndexResponse response = createIndexRequestBuilder.execute().actionGet();
        if(response.isAcknowledged()){
            System.out.println("Index created.");
        }else{
            System.err.println("Index created failed.");
        }
        client.close();
    }
}
