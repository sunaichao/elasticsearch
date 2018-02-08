package com.chit.create;

import org.elasticsearch.action.index.IndexResponse;
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
public class DocAdd {
    public void docAdd(String indexName, String typeName,String value) throws Exception{
        //如果elasticsearch服务端集群名称不是默认的，需要设置setting
        Settings settings = Settings.settingsBuilder().put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));
        IndexResponse response = client.prepareIndex(indexName,typeName,value).setSource(
                //这里可以直接用json字符串
                jsonBuilder()
                        .startObject()
                        .field("type",typeName)
                        .field("eventCount",value)
                        .field("eventDate",new Date())
                        .field("message","secilog insert doc test")
                        .endObject()
        ).get();
        System.out.println("index:" + response.getIndex() + " insert doc id:" + response.getId() + " result:" + response.isCreated());
        client.close();

    }
}
