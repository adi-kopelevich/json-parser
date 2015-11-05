package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class NameValuePairNodeTest {

    @Test
    public void whenGivenKeyValueNodeThenToStringReturnsSeperatedByColon() throws Exception {
        String key = UUID.randomUUID().toString();
        String val = UUID.randomUUID().toString();
        String keyValueExpectedResult = "\"" + key + "\":\"" + val + "\"";
        StringValueNode valueNode = new StringValueNode(val);
        NameValuePairNode keyValueNode = new NameValuePairNode(key, valueNode);
        Assert.assertEquals(keyValueExpectedResult, keyValueNode.toJsonString());
    }
}
