package sample.tree.node.parser.json;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class StringValueNode extends SimpleValueNode {

    public StringValueNode(String jsonString) {
        super.value = jsonString;
    }

    @Override
    public String toJsonString() {
            return JsonConstants.JSON_STRING_DOUBLE_QUOTES + value + JsonConstants.JSON_STRING_DOUBLE_QUOTES;
    }

    public String getValue(){
        return value;
    }

}
