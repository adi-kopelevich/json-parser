package sample.tree.node.parser.json;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 20/11/2015.
 */
public class JsonMarshallerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void whenGivingNullThenThrowMarshallException() {
        expectedException.expect(ParseException.class);
        expectedException.expectMessage(MarshallingErrorMessages.NULL_OBJ);
        new JsonMarshaller().marshall(null);
    }

    @Test
    public void whenGivingObjectedNotAnnotatedThenThrowMarshallException() {
        expectedException.expect(ParseException.class);
        expectedException.expectMessage(MarshallingErrorMessages.OBJ_NOT_ANNOTATED);
        new JsonMarshaller().marshall(new String());
    }

    @Test
    public void whenGivingObjectedWithNoFieldsThenEmptyJsonNodeIsReturned() {
        StringBuilder expectedJsonFormat = new StringBuilder();
        expectedJsonFormat.append(JsonConstants.JSON_OBJECT_OPEN_BRACKET);
        expectedJsonFormat.append(JsonConstants.JSON_OBJECT_CLOSE_BRACKET);
        ValueNode jsonTree = new JsonMarshaller().marshall(new EmptyObj());
        Assert.assertEquals(expectedJsonFormat.toString(), jsonTree.toJsonString());
    }


    @Test
    public void whenGivingObjectWithPublicStringMemberThenReturnJsonValueRepresentingThisObj() throws IllegalAccessException {
        ObjWithStringMember objWithStringMembers = new ObjWithStringMember();
        objWithStringMembers.setStringMember(UUID.randomUUID().toString());
        ValueNode jsonRootNode = new JsonMarshaller().marshall(objWithStringMembers);
        Assert.assertEquals(getObjExpectedJsonFormat(objWithStringMembers), jsonRootNode.toJsonString());
    }

    @Test
    public void whenGivingObjectWithPublicMembersThenReturnJsonValueRepresentingThisObj() throws IllegalAccessException {
        ObjWithPublicMembers objWithStringMembers = new ObjWithPublicMembers();
        objWithStringMembers.setBooleanMember(new Random().nextBoolean());
        objWithStringMembers.setBigBooleanMember(new Random().nextBoolean());
        objWithStringMembers.setStringMember(UUID.randomUUID().toString());
        objWithStringMembers.setIntMember(new Random().nextInt());
        objWithStringMembers.setBigIntMember(new Random().nextInt());
        System.out.println(getObjExpectedJsonFormat(objWithStringMembers));
        ValueNode jsonRootNode = new JsonMarshaller().marshall(objWithStringMembers);
        Assert.assertEquals(getObjExpectedJsonFormat(objWithStringMembers), jsonRootNode.toJsonString());
    }

    @Test
    public void whenGivingObjectWithNumberMembersThenReturnJsonValueRepresentingThisObj() throws IllegalAccessException {
        ObjWithNumberMembers objWithStringMembers = new ObjWithNumberMembers();
        objWithStringMembers.setShortMember((short) new Random().nextInt(Short.MAX_VALUE + 1));
        objWithStringMembers.setBigShortMember((short) new Random().nextInt(Short.MAX_VALUE + 1));
        objWithStringMembers.setIntMember(new Random().nextInt());
        objWithStringMembers.setBigIntMember(new Random().nextInt());
        objWithStringMembers.setLongMember(new Random().nextLong());
        objWithStringMembers.setBigLongMember(new Random().nextLong());
        objWithStringMembers.setFloatMember(new Random().nextFloat());
        objWithStringMembers.setBigFloatMember(new Random().nextFloat());
        objWithStringMembers.setDoubleMember(new Random().nextDouble());
        objWithStringMembers.setBigDoubletMember(new Random().nextDouble());

        System.out.println(getObjExpectedJsonFormat(objWithStringMembers));
        ValueNode jsonRootNode = new JsonMarshaller().marshall(objWithStringMembers);
        Assert.assertEquals(getObjExpectedJsonFormat(objWithStringMembers), jsonRootNode.toJsonString());
    }

    @Test
    public void whenGivingObjectWithArrayMembersThenReturnJsonValueRepresentingThisObj() throws IllegalAccessException {
        ObjWithArrayMembers objWithStringMembers = new ObjWithArrayMembers();
        objWithStringMembers.setShortArray(new short[]{(short) new Random().nextInt(Short.MAX_VALUE + 1), (short) new Random().nextInt(Short.MAX_VALUE + 1)});
        objWithStringMembers.setBigShortArray(new Short[]{(short) new Random().nextInt(Short.MAX_VALUE + 1), (short) new Random().nextInt(Short.MAX_VALUE + 1)});
        //todo add support for all number types including string and boolean arrays
        ValueNode jsonRootNode = new JsonMarshaller().marshall(objWithStringMembers);
        Assert.assertEquals(getObjExpectedJsonFormat(objWithStringMembers), jsonRootNode.toJsonString());
    }


    private String getObjExpectedJsonFormat(Object object) throws IllegalAccessException {
        Class objectClass = object.getClass();
        StringBuilder expectedJsonFormat = new StringBuilder();
        expectedJsonFormat.append(JsonConstants.JSON_OBJECT_OPEN_BRACKET);
        for (Field field : objectClass.getFields()) {
            // add name-value pair per field
            Class fieldClass = field.getType();
            // add field name
            expectedJsonFormat.append(JsonConstants.JSON_STRING_DOUBLE_QUOTES)
                    .append(field.getName())
                    .append(JsonConstants.JSON_STRING_DOUBLE_QUOTES);
            // add name-value separator
            expectedJsonFormat.append(JsonConstants.JSON_NAME_VALUE_SEPARATOR);
            if (fieldClass.equals(Boolean.TYPE) || fieldClass.equals(Boolean.class)) {
                expectedJsonFormat.append(field.get(object));
            } else if (fieldClass.equals(String.class)) {
                expectedJsonFormat.append(JsonConstants.JSON_STRING_DOUBLE_QUOTES)
                        .append(field.get(object))
                        .append(JsonConstants.JSON_STRING_DOUBLE_QUOTES);
            } else if (Short.TYPE.equals(fieldClass)
                    || Integer.TYPE.equals(fieldClass)
                    || Long.TYPE.equals(fieldClass)
                    || Float.TYPE.equals(fieldClass)
                    || Double.TYPE.equals(fieldClass)
                    || Number.class.isAssignableFrom(fieldClass)) {
                expectedJsonFormat.append(field.get(object));
            } else if (fieldClass.isArray()) {
                expectedJsonFormat.append(JsonConstants.JSON_ARRAY_OPEN_BRACKET);
                Object arrayObj = field.get(object);
                Class arrayComponentType = arrayObj.getClass().getComponentType();
                boolean emptyArray = true;
                if (arrayComponentType.equals(Short.TYPE)) {
                    short[] shortArray = (short[]) arrayObj;
                    emptyArray = shortArray.length == 0;
                    for (short shortVal : shortArray) {
                        expectedJsonFormat.append(shortVal);
                        expectedJsonFormat.append(JsonConstants.JSON_FIELD_SEPARATOR);
                    }
                } else if (Number.class.isAssignableFrom(arrayComponentType)) {
                    Number[] numberArray = (Number[]) arrayObj;
                    emptyArray = numberArray.length == 0;
                    for (Number numberValue : numberArray) {
                        expectedJsonFormat.append(numberValue);
                        expectedJsonFormat.append(JsonConstants.JSON_FIELD_SEPARATOR);
                    }
                }
                if (emptyArray) {
                    expectedJsonFormat.append(JsonConstants.JSON_ARRAY_CLOSE_BRACKET);
                } else {
                    expectedJsonFormat.replace(expectedJsonFormat.lastIndexOf(JsonConstants.JSON_FIELD_SEPARATOR),
                            expectedJsonFormat.length(),
                            JsonConstants.JSON_ARRAY_CLOSE_BRACKET);
                }
            }
            expectedJsonFormat.append(JsonConstants.JSON_FIELD_SEPARATOR);
        }
        expectedJsonFormat.replace(expectedJsonFormat.lastIndexOf(JsonConstants.JSON_FIELD_SEPARATOR),
                expectedJsonFormat.length(),
                JsonConstants.JSON_OBJECT_CLOSE_BRACKET);

        return expectedJsonFormat.toString();
    }


}
