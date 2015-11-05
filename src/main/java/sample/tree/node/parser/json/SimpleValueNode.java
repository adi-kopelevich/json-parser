package sample.tree.node.parser.json;

import sample.tree.node.parser.json.ValueNode;

/**
 * Created by kopelevi on 20/09/2015.
 */
public abstract class  SimpleValueNode implements ValueNode {

    protected String value;

    @Override
    public String toJsonString() {
        return value;
    }
}
