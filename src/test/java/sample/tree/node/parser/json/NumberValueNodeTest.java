package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sample.tree.node.parser.json.ValueNode;
import sample.tree.node.parser.json.NumberValueNode;

import java.util.Random;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class NumberValueNodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenFloaNodeThenToStringReturnJsonFloat() throws Exception {
        Float intVal = new Random().nextFloat();
        String jsonVal = String.valueOf(intVal);
        ValueNode node = new NumberValueNode(jsonVal);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }

    @Test
    public void whenDoubleNodeThenToStringReturnJsonDouble() throws Exception {
        Double intVal = new Random().nextDouble();
        String jsonVal = String.valueOf(intVal);
        ValueNode node = new NumberValueNode(jsonVal);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }

    @Test
    public void whenIntegerNodeThenToStringReturnJsonInteger() throws Exception {
        Integer intVal = new Random().nextInt();
        String jsonVal = String.valueOf(intVal);
        ValueNode node = new NumberValueNode(jsonVal);
        Assert.assertEquals(jsonVal, node.toJsonString());
    }

}
