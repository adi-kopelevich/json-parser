package sample.tree.node.parser.json;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ArrayNode extends ComplexNode {

    @Override
    public String getOpenBracket() {
        return JsonConstants.JSON_ARRAY_OPEN_BRACKET;
    }

    @Override
    public String getCloseBracket() {
        return JsonConstants.JSON_ARRAY_CLOSE_BRACKET;
    }


}
