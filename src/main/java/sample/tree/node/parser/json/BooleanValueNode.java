package sample.tree.node.parser.json;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class BooleanValueNode extends SimpleValueNode {

    public BooleanValueNode(Boolean value) {
        super.value = String.valueOf(value);
    }

}
