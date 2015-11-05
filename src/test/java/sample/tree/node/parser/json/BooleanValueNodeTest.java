package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sample.tree.node.parser.json.ValueNode;
import sample.tree.node.parser.json.BooleanValueNode;

import java.util.Random;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class BooleanValueNodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenBooleanLeafNodeThenToStringReturnJsonInt() throws Exception {
        Boolean booleanVal = new Random().nextBoolean();
        String jsonVal = String.valueOf(booleanVal);
        ValueNode node = new BooleanValueNode(booleanVal);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }
}
