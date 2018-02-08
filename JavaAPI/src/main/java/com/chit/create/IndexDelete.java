package com.chit.create;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * 删除索引
 * Created by Administrator on 2018/2/8.
 */
public class IndexDelete {

    public void indexDelete(String indexName) throws Exception{
        //ElasticSearch集群中如果没有设置集群名称，则默认客户端即可，如果已经设置了集群名称，则需要设置setting
        Settings settings = Settings.settingsBuilder().put("cluster.name","tcar-elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.28.24.161"),9300));

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("secilog");
        client.admin().indices().delete(deleteIndexRequest);

        client.close();
    }
}
