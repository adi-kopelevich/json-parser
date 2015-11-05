package sample.tree.node.parser.json;

import junit.framework.Assert;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class ParserUtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenGivenTwoStringsThenReturnConcatByComma() throws Exception {
        String str1 = UUID.randomUUID().toString();
        String str2 = UUID.randomUUID().toString();
        String retVal = ParserUtils.concatWithFieldSeparator(str1, str2);
        Assert.assertEquals(str1 + "," + str2, retVal);
    }

    @Test
    public void whenGivenEmptyJsonStringThenParseReturnEmptyObjectNode() throws Exception {
        String emptyObjectString = "{}";
        ValueNode node = ParserUtils.parseFromString(emptyObjectString);
        Assert.assertEquals(emptyObjectString, node.toJsonString());
    }

    @Test
    public void whenGivenSingleNameValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String val = "\"" + UUID.randomUUID().toString() + "\"";
        String keyValueExpectedResult = "{" + key + ":" + val + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenSingleNameNullValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String val = "null";
        String keyValueExpectedResult = "{" + key + ":" + val + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenSingleNameBooleanValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String val = String.valueOf(new Random().nextBoolean());
        String keyValueExpectedResult = "{" + key + ":" + val + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenSingleNameNumberIntValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = UUID.randomUUID().toString();
        String val = String.valueOf(new Random().nextInt());
        String keyValueExpectedResult = "{" + "\"" + key + "\"" + ":" + val + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenSingleNameNumberDoubleValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = UUID.randomUUID().toString();
        String val = String.valueOf(new Random().nextDouble());  // @todo add support for containing 0 after the dot
        String keyValueExpectedResult = "{"+ "\"" + key+ "\""  + ":" + val + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenMultiNameValueJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        String key1 = "\"" + UUID.randomUUID().toString() + "\"";
        String val1 = "\"" + UUID.randomUUID().toString() + "\"";
        String key2 = "\"" + UUID.randomUUID().toString() + "\"";
        String val2 = "\"" + UUID.randomUUID().toString() + "\"";
        String keyValueExpectedResult = "{" + key1 + ":" + val1 + "," + key2 + ":" + val2 + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGiveNameValueWithJsonObjectThenParseReturnObjectNodeWithThatField() throws Exception {
        String key1 = "\"" + UUID.randomUUID().toString() + "\"";
        String val1 = "\"" + UUID.randomUUID().toString() + "\"";
        String key2 = "\"" + UUID.randomUUID().toString() + "\"";
        String val2 = "true";
        String key3 = "\"" + UUID.randomUUID().toString() + "\"";
        String val3 = "null";
        String key4 = "\"" + UUID.randomUUID().toString() + "\"";
        String val4 = String.valueOf(new Random().nextDouble());
        String keyValueExpectedResult = "{" + key1 + ":{" + key1 + ":" + val1 + "," + key2 + ":" + val2 + "}," + key3 + ":" + val3 + "," + key4 + ":" + val4 + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGiveEmptyArrayJsonObjectThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String keyValueExpectedResult = "{" + key + ":" + "[" + "]" + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGiveArrayJsonObjectThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String val1 = "\"" + UUID.randomUUID().toString() + "\"";
        String val2 = "true";
        String val3 = "null";
        String val4 = String.valueOf(new Random().nextDouble());
        String val5 = "[]";
        String val6 = "{}";
        String keyValueExpectedResult = "{" + key + ":" + "[" + val1 + "," + val2 + "," + val3 + "," + val4 + "," + val5 + "," + val6 + "]" + "}";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGiveArrayJsonThenParseReturnObjectNodeWithThatField() throws Exception {
        String key = "\"" + UUID.randomUUID().toString() + "\"";
        String val1 = "\"" + UUID.randomUUID().toString() + "\"";
        String val2 = "true";
        String val3 = "null";
        NumberFormat formatter = new DecimalFormat("#0.00");
        String val4 = String.valueOf(formatter.format(new Random().nextDouble()));
        String val5 = "[]";
        String val6 = "{}";
        String keyValueExpectedResult = "[{" + key + ":" + "[" + val1 + "," + val2 + "," + val3 + "," + val4 + "," + val5 + "," + val6 + "]" + "},{}]";
        ValueNode node = ParserUtils.parseFromString(keyValueExpectedResult);
        Assert.assertEquals(keyValueExpectedResult, node.toJsonString());
    }

    @Test
    public void whenGivenComplexJsonStringThenParseReturnObjectNodeWithThatField() throws Exception {
        ValueNode node = ParserUtils.parseFromString(getLongJson());
        Assert.assertEquals(getLongJson(), node.toJsonString());
    }

    @Test
    public void whenGivenInvalidJsonWhereObjectDontContainNameValueExceptionIsThrown() throws Exception {
        expectedException.expect(ParseException.class);
        String key1 = "{\"" + UUID.randomUUID().toString() + "\"}";
        ParserUtils.parseFromString(key1);
    }


    @Test
    public void whenGivingAFileThatContainValidJsonThenItIsParseable() throws IOException {
        String jsonFilePath = "tmp.json";
        File file = new File(jsonFilePath);
        file.createNewFile();

        try (Writer writer = new FileWriter(file)) {
            writer.write(getLongJson());
            writer.flush();

        } catch (Exception e) {

        }

        ValueNode parsedJson = ParserUtils.parseFromFile(jsonFilePath);
        Assert.assertEquals(getLongJson(), parsedJson.toJsonString());

        file.delete();
    }

    private String formatJsonString(String str) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(str, JsonNode.class);
        return node.toString();
    }

    private String getLongJson() throws IOException {


        String str = "{" +
                "    \"_id\": \"55fec4a12c159af61985a73f\"," +
                "    \"index\": 0," +
                "    \"guid\": \"0620b1c0-9188-49db-9c70-b1c642b74037\"," +
                "    \"isActive\": true," +
                "    \"balance\": \"$3,325.20\"," +
                "    \"picture\": \"http://placehold.it/32x32\"," +
                "    \"age\": 28," +
                "    \"eyeColor\": \"blue\"," +
                "    \"name\": \"Michael Peck\"," +
                "    \"gender\": \"female\"," +
                "    \"company\": \"HOUSEDOWN\"," +
                "    \"email\": \"michaelpeck@housedown.com\"," +
                "    \"phone\": \"+1 (935) 467-3896\"," +
                "    \"address\": \"704 College Place, Floris, South Carolina, 1279\"," +
                "    \"about\": \"Consequat pariatur magna voluptate nisi consectetur nostrud veniam commodo. Quis minim pariatur qui irure culpa elit fugiat dolor labore Lorem est exercitation. Incididunt sint culpa aute enim ipsum irure sint.\"," +
                "    \"registered\": \"2015-09-07T08:15:22 -03:00\"," +
                "    \"latitude\": -27.339839," +
                "    \"longitude\": -3.673509," +
                "    \"tags\": [" +
                "      \"aliqua\"," +
                "      \"nostrud\"," +
                "      \"dolor\"," +
                "      \"do\"," +
                "      \"eiusmod\"," +
                "      \"et\"," +
                "      \"aute\"" +
                "    ]," +
                "    \"friends\": [" +
                "      {" +
                "        \"id\": 0," +
                "        \"name\": \"Lynch Greene\"" +
                "      }," +
                "      {" +
                "        \"id\": 1," +
                "        \"name\": \"Stafford Tyson\"" +
                "      }," +
                "      {" +
                "        \"id\": 2," +
                "        \"name\": \"Ramirez Johns\"" +
                "      }" +
                "    ]," +
                "    \"greeting\": \"Hello, Michael Peck! You have 4 unread messages.\"," +
                "    \"favoriteFruit\": \"strawberry\"" +
                "  }";

        return formatJsonString(str);
    }

}
