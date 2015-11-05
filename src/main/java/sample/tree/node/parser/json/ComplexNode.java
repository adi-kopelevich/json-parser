package sample.tree.node.parser.json;

import sample.tree.node.parser.json.ValueNode;
import sample.tree.node.parser.json.ParserUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopelevi on 17/09/2015.
 */
public abstract class ComplexNode implements ValueNode {

    private final List<ValueNode> nodes = new ArrayList<ValueNode>();

    public void addNode(ValueNode node) {
        nodes.add(node);
    }

    public abstract String getOpenBracket();

    public abstract String getCloseBracket();

    @Override
    public String toJsonString() {
        return new StringBuilder()
                .append(getOpenBracket())
                .append(getChildNodesStringFormat())
                .append(getCloseBracket()).toString();
    }

    private String getChildNodesStringFormat() {
        String childNodeStrings;
        if (nodes.size() == 0) {
            childNodeStrings = "";
        } else {
            childNodeStrings = nodes.stream()
                    .map(ValueNode::toJsonString)
                    .reduce(ParserUtils::concatWithFieldSeparator)
                    .get();
        }
        return childNodeStrings;
    }





}
