package com.chit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //创建索引
        /**
         IndexCreate indexCreate = new IndexCreate();
         indexCreate.createIndex("secilog","log");
         System.out.println( "Hello World!" );
         */

        //创建类型-并在类型里增加文档记录
        /**
         DocAdd docAdd = new DocAdd();
         docAdd.docAdd("secilog","syslog","1");
         */
        //查询文档
        /**
         DocQuery docQuery = new DocQuery();
         docQuery.docQuery("secilog","syslog","1");
         */
        //更新文档
        /**
         DocUpdate docUpdate = new DocUpdate();
         docUpdate.docUpdate("secilog","syslog","2");
         *
         */
        //删除文档
        /**
         DocDel docDel = new DocDel();
         docDel.docDel("secilog","syslog","1");
         */
        //删除索引
        /**
        IndexDelete indexDelete = new IndexDelete();
        indexDelete.indexDelete("secilog");
         */

    }
}
