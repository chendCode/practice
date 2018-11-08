package com.chendi.practice.mongodb;


import com.chendi.practice.mongodb.utils.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class MongoDemo 
{
  public  static String hostName="127.0.0.1";
  
  public  static int port=27017;
  
  public static  String dbName="demo";
  
  public void showDetails(FindIterable<Document> it ){
    MongoCursor<Document> cursor=it.iterator();
    while (cursor.hasNext()) {
      Document document = (Document) cursor.next();
      String key=String.valueOf(document.get("name")) ;
      System.out.println(document);
      System.out.println(key);
    }
    
  }
    public static void main( String[] args )
    {
      //建立连接
      MongoClient client= MongoUtil.getConnection(hostName, port);
      //选择数据库
      MongoDatabase db=client.getDatabase("demo");
      //获取相应的集合
      MongoCollection<Document> collection=db.getCollection("person");
      //数据集合
      List<Document> list=new ArrayList<Document>();
      list.add(new Document("name","软件三班-朱安文"));
      list.add(new Document("name","软件三班-冮导").append("sex", "man"));
      MongoUtil.insert(hostName, port, dbName, "person", list);
      //查询集合中的文档
      FindIterable<Document> it =collection.find(new Document("name", "王刘瞎夫").append("age", 25));
      FindIterable<Document> it2=MongoUtil.getDocumentByCondition("127.0.0.1", 27017, "demo", "student", new Document("name", "软件三班"));
      FindIterable<Document> it3=MongoUtil.getAllDocument("127.0.0.1", 27017, "demo", "student");
      //文档迭代器
      MongoDemo demo=new MongoDemo(); 
      //demo.showDetails(it);
      //demo.showDetails(it2);
      demo.showDetails(it3);
    }
}
