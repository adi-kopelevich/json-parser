package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ArrayNodeTest {

    @Test
    public void whenEmptyArrayOfTreeNodesThenToStringReturnEmptyJsonArray() throws Exception {
        String expectedJson = "[]";
        ArrayNode jsonArray = new ArrayNode();
        Assert.assertEquals(expectedJson, jsonArray.toJsonString());
    }

    @Test
    public void whenArrayOfTreeNodesThenToStringReturnJsonArray() throws Exception {

        String stringVal =   UUID.randomUUID().toString();
        Integer intVal = new Random().nextInt();
        Boolean booleanVal = new Random().nextBoolean();
        String expectedJson = "[\"" + stringVal + "\"," + String.valueOf(intVal) + "," + String.valueOf(booleanVal) + "]";
        StringValueNode stringValNode = new StringValueNode(stringVal);
        NumberValueNode intValNode = new NumberValueNode(intVal.toString());
        BooleanValueNode booleanValNode = new BooleanValueNode(booleanVal);

        ArrayNode jsonArray = new ArrayNode();
        jsonArray.addNode(stringValNode);
        jsonArray.addNode(intValNode);
        jsonArray.addNode(booleanValNode);

        Assert.assertEquals(expectedJson, jsonArray.toJsonString());
    }

}
