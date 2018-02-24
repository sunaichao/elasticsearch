package com.chit.create;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Created by Administrator on 2018/2/8.
 */
public class DocDel {
    public void docDel(String indexName, String typeName, String id) throws Exception{
        //ElasticSearch集群中 如果集群名称已经设置，得先设置setting
        Settings settings = Settings.settingsBuilder().put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));


        DeleteResponse deleteResponse = client.prepareDelete(indexName,typeName,id).get();
        boolean isFound = deleteResponse.isFound();//文档存在返回true，不存在返回false
        System.out.println("是否存在:" + isFound);
        client.close();
    }
}
