package com.chit.create;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Created by Administrator on 2018/2/8.
 */
public class DocQuery {
    public void docQuery(String indexName, String typeName, String index) throws Exception{
        //如果ElasticSearch集群名称没有设置是默认的 就没有必要设置setting
        Settings settings = Settings.settingsBuilder().put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));
        GetResponse response = client.prepareGet(indexName,typeName,index).get();
        System.out.println(response.getSource().toString());
        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        client.close();
    }
}
