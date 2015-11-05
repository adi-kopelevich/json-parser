package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ObjectNodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenGivenEmptyTreeThenToStringReturnEmptyJson() throws Exception {
        String expectedJson = "{}";
        ObjectNode jsonObjectNode = new ObjectNode();
        Assert.assertEquals(expectedJson, jsonObjectNode.toJsonString());
    }

    @Test
    public void whenAddingNoneKeyValueThenInvalidParamThrown() throws Exception {
        expectedException.expect(InvalidParameterException.class);
        ObjectNode jsonObjectNode = new ObjectNode();
        String intValueStr = String.valueOf(new Random().nextInt());
        jsonObjectNode.addNode(new NumberValueNode(intValueStr));
    }

    @Test
    public void whenGivenSingleKeyValueNodeThenToStringReturnJsonReprsentingThatKeyValue() throws Exception {
        String key = UUID.randomUUID().toString();
        String val = UUID.randomUUID().toString();
        String expectedJson = "{" + "\"" + key + "\"" + ":" + "\"" + val + "\"" + "}";
        StringValueNode valNode = new StringValueNode(val);
        NameValuePairNode keyValueNode = new NameValuePairNode(key, valNode);
        ObjectNode jsonObjectTreeNode = new ObjectNode();
        jsonObjectTreeNode.addNode(keyValueNode);
        Assert.assertEquals(expectedJson, jsonObjectTreeNode.toJsonString());
    }

    @Test
    public void whenGivenMultipleKeyValueNodesThenToStringReturnJsonReprsentingThoseKeyValues() throws Exception {
        String key1 = UUID.randomUUID().toString();
        String val1 = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();
        Integer val2 = new Random().nextInt();
        String key3 = UUID.randomUUID().toString();
        Boolean val3 = new Random().nextBoolean();
        String expectedJson = "{" + "\"" + key1 + "\"" + ":" + "\"" + val1 + "\"" + ""
                + "," + "\"" + key2 + "\"" + ":" + String.valueOf(val2)
                + "," + "\"" + key3 + "\"" + ":" + String.valueOf(val3) + "}";

        StringValueNode valNode1 = new StringValueNode(val1);
        NameValuePairNode keyValueNode1 = new NameValuePairNode(key1, valNode1);

        NumberValueNode valNode2 = new NumberValueNode(String.valueOf(val2));
        NameValuePairNode keyValueNode2 = new NameValuePairNode(key2, valNode2);

        BooleanValueNode valNode3 = new BooleanValueNode(val3);
        NameValuePairNode keyValueNode3 = new NameValuePairNode(key3, valNode3);

        ObjectNode jsonObjectTreeNode = new ObjectNode();
        jsonObjectTreeNode.addNode(keyValueNode1);
        jsonObjectTreeNode.addNode(keyValueNode2);
        jsonObjectTreeNode.addNode(keyValueNode3);
        Assert.assertEquals(expectedJson, jsonObjectTreeNode.toJsonString());
    }

    @Test
    public void whenGivingEmptyJsonObjectThenAnEmptyObjectNodeReturned() throws Exception {
        String jsonString = "{}";
        ValueNode jsonValueNode = ParserUtils.parseFromString(jsonString);
        Assert.assertEquals(jsonString, jsonValueNode.toJsonString());
    }

    @Test
    public void whenGivingSingleNameValueJsonObjectThenAnAppropriteNodeReturned() throws Exception {
        String key = UUID.randomUUID().toString();
        String val = UUID.randomUUID().toString();
        String keyValueExpectedResult = "{" + "\"" + key + "\"" + ":" + "\"" + val + "\"" + "}";

        ValueNode jsonValueNode = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, jsonValueNode.toJsonString());
    }

    @Test
    public void whenGivingMultipleNameValueJsonObjectThenAnAppropriteNodeReturned() throws Exception {
        String key1 = UUID.randomUUID().toString();
        String val1 = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();
        String val2 = UUID.randomUUID().toString();
        String keyValueExpectedResult = "{" + "\"" + key1 + "\"" + ":" + "\"" + val1 + "\"" + "," + "\"" + key2 + "\"" + ":" + "\"" + val2 + "\"" + "}";

        ValueNode jsonValueNode = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, jsonValueNode.toJsonString());
    }
}
