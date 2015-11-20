package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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
                String fieldName = field.getName();
                Class fieldClass = field.getType();
                ValueNode valueNode = null;
                if (fieldClass.equals(boolean.class) || fieldClass.equals(Boolean.class)) {
                    boolean fieldValue = (boolean) field.get(object);
                    valueNode = new BooleanValueNode(fieldValue);
                } else if (fieldClass.equals(String.class)) {
                    String fieldValue = (String) field.get(object);
                    valueNode = new StringValueNode(fieldValue);
                } else if (Short.TYPE.equals(fieldClass)
                        || Integer.TYPE.equals(fieldClass)
                        || Long.TYPE.equals(fieldClass)
                        || Float.TYPE.equals(fieldClass)
                        || Double.TYPE.equals(fieldClass)
                        || Number.class.isAssignableFrom(fieldClass)) {
                    valueNode = new NumberValueNode(field.get(object).toString()); //todo parse by number
                } else if (fieldClass.isArray()) {  // todo add support for all primitives number and big numbers, boolean and string array
                    ArrayNode arrayNode = new ArrayNode();
                    Object arrayObject = field.get(object);
                    Class arrayComponentType = arrayObject.getClass().getComponentType();
                    if (Short.TYPE.equals(arrayComponentType)) {
                        short[] shortArray = (short[]) arrayObject;
                        for (short shortValue : shortArray) {
                            arrayNode.addNode(new NumberValueNode(String.valueOf(shortValue)));
                        }
                    } else if (Number.class.isAssignableFrom(arrayComponentType)) {
                        Number[] numberArray = (Number[]) arrayObject;
                        for (Number num : numberArray) {
                            arrayNode.addNode(new NumberValueNode(num.toString()));
                        }
                    }
                    valueNode = arrayNode;
                }
                rootJsonObject.addNode(new NameValuePairNode(fieldName, valueNode));
            }
        } catch (IllegalAccessException e) {
            new ParseException("Failed to marshall given object", e);
        }
        return rootJsonObject;
    }

}
