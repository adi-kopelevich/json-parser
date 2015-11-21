package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopelevi on 20/11/2015.
 */
public class JsonMarshaller {

    public ValueNode marshall(Object object) throws ParseException {
        if (object == null) {
            throw new ParseException(MarshallingErrorMessages.NULL_OBJ);
        }
        Class objectClass = object.getClass();
        Annotation[] jsonObjAnnotation = objectClass.getAnnotationsByType(JsonObjectAnnotation.class);
        if (jsonObjAnnotation == null || jsonObjAnnotation.length != 1) {
            throw new ParseException(MarshallingErrorMessages.OBJ_NOT_ANNOTATED);
        }
        ObjectNode rootJsonObject = new ObjectNode();
        try {
            for (Field field : objectClass.getFields()) {
                ValueNode valueNode = null;
                Class fieldClass = field.getType();
                if (isTypeBoolean(fieldClass)) {
                    valueNode = new BooleanValueNode((boolean) field.get(object));
                } else if (isTypeString(fieldClass)) {
                    valueNode = new StringValueNode((String) field.get(object));
                } else if (isTypeNumber(fieldClass)) {
                    valueNode = new NumberValueNode(field.get(object).toString()); //todo parse by number
                } else if (isTypeArray(fieldClass)) {  // todo boolean and string array
                    valueNode = handleArrayNode(field, object);
                }
                // todo add add support to collections, in a similar way done for arrays
                // todo add support for recuresive objetcs valueNode = marshall(object)
                rootJsonObject.addNode(new NameValuePairNode(field.getName(), valueNode));
            }
        } catch (IllegalAccessException e) {
            new ParseException("Failed to marshall given object", e);
        }
        return rootJsonObject;
    }

    private boolean isTypeBoolean(Class c) {
        return c.equals(boolean.class) || c.equals(Boolean.class);
    }

    private boolean isTypeString(Class c) {
        return c.equals(String.class);
    }

    private boolean isTypeNumber(Class c) {
        return Short.TYPE.equals(c)
                || Integer.TYPE.equals(c)
                || Long.TYPE.equals(c)
                || Float.TYPE.equals(c)
                || Double.TYPE.equals(c)
                || Number.class.isAssignableFrom(c);
    }

    private boolean isTypeArray(Class c) {
        return c.isArray();
    }

    private List<ValueNode> getListOfShortNodes(short[] shortArray) {
        List<ValueNode> nodeList = new ArrayList<>();
        for (short shortValue : shortArray) {
            nodeList.add(new NumberValueNode(String.valueOf(shortValue)));
        }
        return nodeList;
    }

    private List<ValueNode> getListOfIntNodes(int[] intArray) {
        List<ValueNode> nodeList = new ArrayList<>();
        for (int intValue : intArray) {
            nodeList.add(new NumberValueNode(String.valueOf(intValue)));
        }
        return nodeList;
    }

    private List<ValueNode> getListOfLongNodes(long[] longArray) {
        List<ValueNode> nodeList = new ArrayList<>();
        for (long longValue : longArray) {
            nodeList.add(new NumberValueNode(String.valueOf(longValue)));
        }
        return nodeList;
    }

    private List<ValueNode> getListOfFloatNodes(float[] floatArray) {
        List<ValueNode> nodeList = new ArrayList<>();
        for (float floatValue : floatArray) {
            nodeList.add(new NumberValueNode(String.valueOf(floatValue)));
        }
        return nodeList;
    }

    private List<ValueNode> getListOfDoubleNodes(double[] doubleArray) {
        List<ValueNode> nodeList = new ArrayList<>();
        for (double doubleValue : doubleArray) {
            nodeList.add(new NumberValueNode(String.valueOf(doubleValue)));
        }
        return nodeList;
    }


    private ArrayNode handleArrayNode(Field field, Object object) throws IllegalAccessException {
        ArrayNode arrayNode = new ArrayNode();
        Object arrayObject = field.get(object);
        Class arrayComponentType = arrayObject.getClass().getComponentType();
        if (Short.TYPE.equals(arrayComponentType)) {
            arrayNode.addNode(getListOfShortNodes((short[]) arrayObject));
        } else if (Integer.TYPE.equals(arrayComponentType)) {
            arrayNode.addNode(getListOfIntNodes((int[]) arrayObject));
        } else if (Long.TYPE.equals(arrayComponentType)) {
            arrayNode.addNode(getListOfLongNodes((long[]) arrayObject));
        } else if (Float.TYPE.equals(arrayComponentType)) {
            arrayNode.addNode(getListOfFloatNodes((float[]) arrayObject));
        } else if (Double.TYPE.equals(arrayComponentType)) {
            arrayNode.addNode(getListOfDoubleNodes((double[]) arrayObject));
        } else if (Number.class.isAssignableFrom(arrayComponentType)) {
            Number[] numberArray = (Number[]) arrayObject;
            for (Number num : numberArray) {
                arrayNode.addNode(new NumberValueNode(num.toString()));
            }
        }
        return arrayNode;
    }

}
