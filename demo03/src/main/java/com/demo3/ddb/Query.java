package com.demo3.ddb;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.net.URI;
import java.util.HashMap;
// snippet-end:[dynamodb.java2.query.import]

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 *
 *  To query items from an Amazon DynamoDB table using the AWS SDK for Java V2,
 *  its better practice to use the
 *  Enhanced Client. See the EnhancedQueryRecords example.
 */
public class Query {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage:\n" +
                "    <tableName> <partitionKeyName> <partitionKeyVal>\n\n" +
                "Where:\n" +
                "    tableName - The Amazon DynamoDB table to put the item in (for example, Music3).\n" +
                "    partitionKeyName - The partition key name of the Amazon DynamoDB table (for example, Artist).\n" +
                "    partitionKeyVal - The value of the partition key that should match (for example, Famous Band).\n\n" ;

//        if (args.length != 3) {
//            System.out.println(usage);
//            System.exit(1);
//        }

        String tableName = "Music";
        String partitionKeyName = "Artist";
        String partitionKeyVal = "No One You Know";

        // For more information about an alias, see:
        // https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionAttributeNames.html
        String partitionAlias = "";

        System.out.format("Querying %s", tableName);
        System.out.println("");

        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = null;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .credentialsProvider(credentialsProvider)
                .region(Region.CN_NORTH_1)
                .build();

        int count = queryTable(ddb, tableName, partitionKeyName, partitionKeyVal,partitionAlias ) ;
        System.out.println("There were "+count + "  record(s) returned");
        ddb.close();
    }

    // snippet-start:[dynamodb.java2.query.main]
    public static int queryTable(DynamoDbClient ddb, String tableName, String partitionKeyName, String partitionKeyVal, String partitionAlias) {

        // Set up an alias for the partition key name in case it's a reserved word.
        HashMap<String,String> attrNameAlias = new HashMap<String,String>();
        attrNameAlias.put(partitionAlias, partitionKeyName);

        // Set up mapping of the partition name with the value.
        HashMap<String, AttributeValue> attrValues = new HashMap<>();

        attrValues.put(":"+partitionKeyName, AttributeValue.builder()
                .s(partitionKeyVal)
                .build());

        QueryRequest queryReq = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression(partitionAlias + " = :" + partitionKeyName)
                .expressionAttributeNames(attrNameAlias)
                .expressionAttributeValues(attrValues)
                .build();

        System.out.println(queryReq.toString());
        try {
            QueryResponse response = ddb.query(queryReq);
            return response.count();

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return -1;
    }
}
