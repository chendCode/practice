package com.chendi.practice.mongodb.utils;

import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoUtil {

  /**
   * 方法名：  getConnection	<br>
   * 方法描述：获取数据库连接
   * 修改备注：<br>
   * 创建时间： 2016年12月9日上午10:41:28<br>
   * @param host 数据库连接ip
   * @param port 数据库连接端口
   * @return
   */
  public static MongoClient getConnection(String host, int port){
    MongoClient client=new MongoClient(host, port);
    return client;
  }
  /**
   * 方法名：  getDatabase	<br>
   * 方法描述：获取指定数据库实例
   * 修改备注：<br>
   * 创建时间： 2016年12月9日上午10:32:13<br>
   * @param host mongo数据库主机ip
   * @param port 端口号
   * @param DbName 选择的数据库
   * @return
   */
  public static MongoDatabase getDatabase(String host, int port, String DbName){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    return db;
  }
  
  /**
   * 方法名：  getAllDocument	<br>
   * 方法描述：获取集合中所有文档信息
   * 修改备注：<br>
   * 创建时间： 2016年12月9日上午11:25:01<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName
   * @return
   */
  public static FindIterable<Document> getAllDocument(String host, int port, String DbName, String collectionName){
    MongoClient client=new MongoClient(host, port);
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    FindIterable<Document> cursor=collection.find();
    return cursor;
  }
  /**
   * 方法名：  getDocumentByCondition	<br>
   * 方法描述：根据条件查询集合中的文档
   * 修改备注：<br>
   * 创建时间： 2016年12月9日上午11:33:38<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName
   * @param conditionBson
   * @return
   */
  public static FindIterable<Document> getDocumentByCondition(String host,int port,String DbName,String collectionName,Document conditionBson){
    MongoClient client=new MongoClient(host, port);
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    FindIterable<Document> cursor=collection.find(conditionBson);
    return cursor;
  }
  /**
   * 方法名：  insertgetDatabase	<br>
   * 方法描述： 向指定数据库集合中插入文档
   * 修改备注：<br>
   * 创建时间： 2016年12月9日上午11:48:19<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName
   * @param list
   */
  public static void insert(String host,int port,String DbName,String collectionName,List<Document> list){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.insertMany(list);
  }
   /**
    * 方法名：  updateOne	<br>
    * 方法描述： 更新条件匹配第一条
    * 修改备注：<br>
    * 创建时间： 2016年12月9日下午2:23:04<br>
    * @param host
    * @param port
    * @param DbName
    * @param collectionName
    * @param condition  过滤条件bson
    * @param updateData 更新数据bson
    */
  public static void updateOne(String host,int port,String DbName,String collectionName,Document condition,Document updateData){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.updateOne(condition, updateData);
  }
  /**
   * 方法名：  updateMany	<br>
   * 方法描述：批量更新数据
   * 修改备注：<br>
   * 创建时间： 2016年12月9日下午3:49:35<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName
   * @param condition
   * @param updateData
   */
  public void updateMany(String host,int port,String DbName,String collectionName,Document condition,Document updateData){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.updateMany(condition, updateData);
  }
  /**
   * 方法名：  removeByCondition	<br>
   * 方法描述：删除所有符合条件的数据
   * 修改备注：<br>
   * 创建时间： 2016年12月9日下午3:51:27<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName 集合名 
   * @param condition 条件
   */
  public void removeByCondition(String host,int port,String DbName,String collectionName,Document condition){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.deleteMany(condition);
  }
  /**
   * 方法名：  removeOneByCondition	<br>
   * 方法描述：删除符合条件的第一条数据
   * 修改备注：<br>
   * 创建时间： 2016年12月9日下午3:55:35<br>
   * @param host 
   * @param port
   * @param DbName
   * @param collectionName 集合名 
   * @param condition 条件
   */
  public void removeOneByCondition(String host,int port,String DbName,String collectionName,Document condition){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.deleteOne(condition);
  }
  /**
   * 方法名：  removeAll	<br>
   * 方法描述：移除集合中所有的数据
   * 修改备注：<br>
   * 创建时间： 2016年12月9日下午4:00:06<br>
   * @param host
   * @param port
   * @param DbName
   * @param collectionName
   */
  public void removeAll(String host,int port,String DbName,String collectionName){
    MongoClient client=new MongoClient(host,port );
    MongoDatabase db=client.getDatabase(DbName);
    MongoCollection<Document> collection=db.getCollection(collectionName);
    collection.deleteOne(new Document());
  }
  
}
