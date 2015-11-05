package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class StringValueNodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenStringLeafNodeThenToStringReturnJsonString() throws Exception {
        String value = UUID.randomUUID().toString();
        String jsonVal = "\"" + value + "\"";
        ValueNode node = new StringValueNode(value);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }

    @Test
    public void whenSpecialCharStringThenToStringReturnJsonString() throws Exception {
        String stringVl = "\"\\/\b\f\n\r\t\u1234";
        String jsonVal = "\"" + stringVl + "\"";
        ValueNode node = new StringValueNode(stringVl);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }

    @Test
    public void whenGivenStringValueJsonThenParsingSucceed() throws Exception {
        String value = UUID.randomUUID().toString();
        String jsonVal = "\"" + value + "\"";
        StringValueNode node = new StringValueNode(value);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }
}
