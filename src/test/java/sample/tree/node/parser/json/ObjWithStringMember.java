package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

/**
 * Created by kopelevi on 20/11/2015.
 */
@JsonObjectAnnotation
public class ObjWithStringMember {

    private Object privateMember;
    public String stringMember;

    public ObjWithStringMember() {

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

}
