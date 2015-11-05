package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sample.tree.node.parser.json.ValueNode;
import sample.tree.node.parser.json.NullValueNode;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class NullValueNodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenNullValueNodeThenToStringReturnNullString() throws Exception {
        String jsonVal = "null";
        ValueNode node = new NullValueNode();
        Assert.assertEquals(jsonVal, node.toJsonString());
    }


}
