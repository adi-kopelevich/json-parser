package sample.tree.node.parser.json;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class NameValuePairNode implements ValueNode {

    private final StringValueNode key;
    private final ValueNode value;

    public NameValuePairNode(String key, ValueNode value) {
//        if (ParserUtils.isStringParseable(key)) {
        this.key = new StringValueNode(key);
        this.value = value;
//        } else {
//            throw new InvalidParameterException("Not a valid key format, given key: " + key);
//        }
    }

    @Override
    public String toJsonString() {
        return new StringBuilder()
//                .append(JsonConstants.JSON_STRING_DOUBLE_QUOTES)
                .append(key.toJsonString())
//                .append(JsonConstants.JSON_STRING_DOUBLE_QUOTES)
                .append(JsonConstants.JSON_NAME_VALUE_SEPARATOR)
                .append(value.toJsonString()).toString();
    }

}
