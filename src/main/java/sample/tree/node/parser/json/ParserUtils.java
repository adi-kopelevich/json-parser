package sample.tree.node.parser.json;

import java.io.*;
import java.util.Stack;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ParserUtils {

    public static ValueNode parseFromFile(String jsonFilePath) throws ParseException {
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            return parse(fileReader);
        } catch (FileNotFoundException e) {
            throw new ParseException("File not found - " + jsonFilePath, e);
        } catch (IOException e) {
            throw new ParseException("Failed to read file - " + jsonFilePath, e);
        }
    }

    public static ValueNode parseFromString(String jsonString) throws ParseException {
        return parse(new StringReader(jsonString));
    }

    private static ValueNode parse(Reader reader) throws ParseException {
        Stack<Object> stack = new Stack<>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        try {
            int token = streamTokenizer.nextToken();
            while (token != StreamTokenizer.TT_EOF) {
                if (streamTokenizer.ttype == StreamTokenizer.TT_EOL) {  // not expected in json
                    throw new ParseException("EOL  was  not expected here. " + streamTokenizer.lineno());
                } else if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) { // boolean or null
                    String word = streamTokenizer.sval;
                    if (JsonConstants.TRUE_VALUE.equals(word) || JsonConstants.FALSE_VALUE.equals(word)) {
                        pushToStackWithRegardsToNameValueSeparator(stack, new BooleanValueNode(Boolean.valueOf(word)));
                    } else if (JsonConstants.NULL_VALUE.equals(word)) {
                        pushToStackWithRegardsToNameValueSeparator(stack, new NullValueNode());
                    } else {
                        throw new ParseException("Word = " + word + " was  not expected here. " + streamTokenizer.lineno());
                    }
                } else if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) { // number
                    Double number = streamTokenizer.nval;
                    NumberValueNode numberValueNode = new NumberValueNode(number);
                    pushToStackWithRegardsToNameValueSeparator(stack, numberValueNode);
                } else if (streamTokenizer.sval != null) {                      // string
                    StringValueNode stringValueNode = new StringValueNode(streamTokenizer.sval);
                    pushToStackWithRegardsToNameValueSeparator(stack, stringValueNode);
                } else {                                                        // json chars
                    String c = String.valueOf((char) token);
                    switch (c) {
                        case JsonConstants.JSON_OBJECT_OPEN_BRACKET:
                            stack.push(JsonConstants.JSON_OBJECT_OPEN_BRACKET);
                            break;
                        case JsonConstants.JSON_OBJECT_CLOSE_BRACKET:
                            closeObjectNode(stack);
                            break;
                        case JsonConstants.JSON_ARRAY_OPEN_BRACKET:
                            stack.push(JsonConstants.JSON_ARRAY_OPEN_BRACKET);
                            break;
                        case JsonConstants.JSON_ARRAY_CLOSE_BRACKET:
                            closeArrayNode(stack);
                            break;
                        case JsonConstants.JSON_FIELD_SEPARATOR:
                            stack.push(c);
                            break;
                        case JsonConstants.JSON_NAME_VALUE_SEPARATOR:
                            stack.push(c);
                            break;
                        default:
                            throw new ParseException("Character = " + c + " was  not expected here. " + streamTokenizer.lineno());
                    }
                }
                token = streamTokenizer.nextToken();
            }
        } catch (Exception e) {
            throw new ParseException("Failed to parse given string to JSON format: " + reader, e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (ValueNode) stack.pop();
    }

    private static void pushToStackWithRegardsToNameValueSeparator(Stack<Object> stack, ValueNode node) {
        if (!stack.empty() && stack.peek().equals(JsonConstants.JSON_NAME_VALUE_SEPARATOR)) {
            stack.pop();
            StringValueNode stringNode = (StringValueNode) stack.pop();
            String key = stringNode.getValue();
            NameValuePairNode nameValuePairNode = new NameValuePairNode(key, node);
            stack.push(nameValuePairNode);
        } else {
            stack.push(node);
        }
    }

    private static void closeObjectNode(Stack<Object> stack) {
        closeComplexNode(stack, new ObjectNode());
    }

    private static void closeArrayNode(Stack<Object> stack) {
        closeComplexNode(stack, new ArrayNode());
    }

    private static void closeComplexNode(Stack<Object> stack, ComplexNode node) {
        Stack<ValueNode> reverseStack = new Stack<>();
        if (stack.peek() instanceof ValueNode) {
            reverseStack.add((ValueNode) stack.pop());
        }
        while (stack.peek().equals(JsonConstants.JSON_FIELD_SEPARATOR)) {
            stack.pop();
            reverseStack.add((ValueNode) stack.pop());
        }
        while (!reverseStack.empty()) {
            node.addNode(reverseStack.pop());
        }
        if (stack.peek().equals(node.getOpenBracket())) {
            stack.pop();
            pushToStackWithRegardsToNameValueSeparator(stack, node);
        } else {
            throw new RuntimeException("Expected to find  " + node.getOpenBracket() + ", got: " + stack.pop().toString());
        }
    }


    public static boolean isStringParseable(String jsonString) {
        return jsonString.indexOf("\"") == 0 && jsonString.lastIndexOf("\"") == jsonString.length() - 1;
    }

    public static String concatWithFieldSeparator(String str1, String str2) {
        return new StringBuilder()
                .append(str1)
                .append(JsonConstants.JSON_FIELD_SEPARATOR)
                .append(str2).toString();
    }
}