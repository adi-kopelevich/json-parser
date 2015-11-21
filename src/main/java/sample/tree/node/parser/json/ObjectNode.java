package sample.tree.node.parser.json;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ObjectNode extends ComplexNode {

    public void addNode(ValueNode node) {
        if (node instanceof NameValuePairNode) {
            super.addNode(node);
        } else {
            throw new InvalidParameterException("Only JSON key-value tree nodes are allowed, given:" + node.toJsonString());
        }
    }

    public void addNode(List<ValueNode> nodeList) {
        for (ValueNode node : nodeList) {
            addNode(node);
        }
    }

    @Override
    public String getOpenBracket() {
        return JsonConstants.JSON_OBJECT_OPEN_BRACKET;
    }

    @Override
    public String getCloseBracket() {
        return JsonConstants.JSON_OBJECT_CLOSE_BRACKET;
    }

}
