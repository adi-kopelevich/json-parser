package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

/**
 * Created by kopelevi on 20/11/2015.
 */
@JsonObjectAnnotation
public class ObjWithPublicMembers {

    private Object privateMember;
    public boolean booleanMember;
    public Boolean bigBooleanMember;
    public String stringMember;
    public int intMember;
    public Integer bigIntMember;

//    private String[] arrayMember;

    public ObjWithPublicMembers() {

    }

    public boolean getBooleanMember() {
        return booleanMember;
    }

    public void setBooleanMember(boolean booleanMember) {
        this.booleanMember = booleanMember;
    }

    public Boolean getBigBooleanMember() {
        return bigBooleanMember;
    }

    public void setBigBooleanMember(Boolean bigBooleanMember) {
        this.bigBooleanMember = bigBooleanMember;
    }

    public Object getPrivateMember() {
        return privateMember;
    }

    public void setPrivateMember(Object privateMember) {
        this.privateMember = privateMember;
    }

    public String getStringMember() {
        return stringMember;
    }

    public void setStringMember(String stringMember) {
        this.stringMember = stringMember;
    }

    public int getIntMember() {
        return intMember;
    }

    public void setIntMember(int intMember) {
        this.intMember = intMember;
    }

    public Integer getBigIntMember() {
        return bigIntMember;
    }

    public void setBigIntMember(Integer bigIntMember) {
        this.bigIntMember = bigIntMember;
    }

//    public String[] getArrayMember() {
//        return arrayMember;
//    }
//
//    public void setArrayMember(String[] arrayMember) {
//        this.arrayMember = arrayMember;
//    }
}
