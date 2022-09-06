package com.demo3.ddb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.*;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author yanmiao.wu
 * @create 2022-09-06 15:24
 */
public class DynamoDBDemo {
    public static AmazonDynamoDB client = getAmazonDynamoDBClient();
    public static DynamoDB dynamoDB = new DynamoDB(client);
    public static Table table = dynamoDB.getTable("Movies");

    public static AmazonDynamoDB getAmazonDynamoDBClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "cn-north-1"))
                .build();
    }

    /**
     * 创建表
     * createTable()
     */
    public void createTable(String tableName) {

        try {
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("year", KeyType.HASH),//分区键
                            new KeySchemaElement("title", KeyType.RANGE)),//排序键
                    Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),//数字
                            new AttributeDefinition("title", ScalarAttributeType.S)),//字符串
                    new ProvisionedThroughput(10L, 10L));//预置吞吐量
            table.waitForActive();
            System.out.println("Success. Table status: " + table.getDescription().getTableStatus());

        } catch (InterruptedException e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 删除表
     * deleteTable()
     */
    public void deleteTable() {
        try {
            table.delete();
            table.waitForDelete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载样本数据
     * loadData()
     */
    public void loadData() {
        try {

            JsonParser parser = new JsonFactory().createParser(new File("D:\\ProjectData\\idea-workspace\\dynamodbcurd\\src\\main\\java\\com\\merlin\\dynamodbcurd\\demo\\data\\moviedata.json"));
            JsonNode rootNode = new ObjectMapper().readTree(parser);
            Iterator<JsonNode> iter = rootNode.iterator();

            ObjectNode currentNode;
            while (iter.hasNext()) {
                currentNode = (ObjectNode) iter.next();
                int year = currentNode.path("year").asInt();
                String title = currentNode.path("title").asText();
                table.putItem(new Item().withPrimaryKey("year", year, "title", title).withJSON("info", currentNode.path("info").toString()));
                System.out.println("PutItem succeeded: " + year + " " + title);
                parser.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 往表中增加数据（项目）
     * putItems
     */
    public void putItems() {
        int year = 2015;
        String title = "The Big New Movie";

        final Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("plot", "Nothing happens at all.");
        infoMap.put("rating", 0);
        PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));
        System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        System.out.println("PutItem succeeded:\n" + outcome.getItem());

    }

    /**
     * 查询数据（根据索引）
     * getItems()
     */
    public void getItems() {
        int year = 2015;
        String title = "The Big New Movie";
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("year", year, "title", title);
        Item outcome = table.getItem(spec);
        System.out.println("GetItem succeeded: " + outcome);
    }

    /**
     * 更新数据
     */
    public void updateItem() {
        int year = 2015;
        String title = "The Big New Movie";

        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("year", year, "title", title)
                .withUpdateExpression("set info.rating = :r, info.plot=:p, info.actors=:a")
                .withValueMap(new ValueMap().withNumber(":r", 5.5)
                        .withString(":p", "Everything happens all at once.")
                        .withList(":a", Arrays.asList("larry", "Moe", "Curly")))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
        System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

    }

    /**
     * 根据条件更新数据
     */
    public void updateItemByConditions() {
        int year = 2015;
        String title = "The Big New Movie";
        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey(new PrimaryKey("year", year, "title", title))
                .withUpdateExpression("remove info.actors[0]")
                .withConditionExpression("size(info.actors)>:num")
                .withValueMap(new ValueMap().withNumber(":num", 2))
                .withReturnValues(ReturnValue.UPDATED_NEW);

        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
        System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
    }

    /**
     * 删除项目
     * deleteItem()
     */
    public void deleteItem() {
        int year = 2015;
        String title = "The Big New Movie";
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey("year", year, "title", title))
                .withConditionExpression("info.rating <= :val")
                .withValueMap(new ValueMap().withNumber(":val", 10.0));
        table.deleteItem(deleteItemSpec);
        System.out.println("DeleteItem succeeded");

    }

    /**
     * 查询数据
     * queryItem
     */
    public void queryItem() {
        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#yr", "year");
        HashMap<String, Object> valueMap = new HashMap<>();
        valueMap.put(":yyyy", 1985);
        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#yr = :yyyy")
                .withNameMap(nameMap)
                .withValueMap(valueMap);
        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        System.out.println("Movies from 1985");
        items = table.query(querySpec);

        iterator = items.iterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.getNumber("year") + ": " + item.getString("title"));

        }
        valueMap.put(":yyyy", 1992);
        valueMap.put(":letter1", "A");
        valueMap.put(":letter2", "L");
        querySpec.withProjectionExpression("#yr,title,info.genres,info.actors[0]")
                .withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2")
                .withNameMap(nameMap)
                .withValueMap(valueMap);
        System.out.println("Movies from 1992 - titles A-L,with genres and lead actor");
        items = table.query(querySpec);

        iterator = items.iterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.getNumber("year") + ": " + item.getString("title") + " " + item.getMap("info"));

        }

    }

    /**
     * 扫描过滤数据
     * scanItem()
     */
    public void scanItem() {
        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#yr", "year");
        HashMap<String, Object> valueMap = new HashMap<>();
        valueMap.put(":start_yr", 1950);
        valueMap.put(":end_yr", 1959);
        ScanSpec scanSpec = new ScanSpec()
                .withProjectionExpression("#yr,title,info.rating")
                .withFilterExpression("#yr between :start_yr and :end_yr")
                .withNameMap(nameMap)
                .withValueMap(valueMap);
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);
        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            System.out.println(item.toString());

        }
    }

    public static void main(String[] args) {
        DynamoDBDemo dynamoDBDemo = new DynamoDBDemo();
        dynamoDBDemo.createTable("Movies");//创建表
//        dynamoDBDemo.deleteTable();//删除表
//        dynamoDBDemo.loadData();//加载数据
//        dynamoDBDemo.putItems();//添加数据
//        dynamoDBDemo.getItems();//查询根据索引
//        dynamoDBDemo.updateItem();//更新
//        dynamoDBDemo.updateItemByConditions();//根据条件更新
//        dynamoDBDemo.deleteItem();//删除项目
//        dynamoDBDemo.queryItem();//查询数据
//        dynamoDBDemo.scanItem();//扫描过滤
    }
}
