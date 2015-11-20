package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

/**
 * Created by kopelevi on 20/11/2015.
 */
@JsonObjectAnnotation
public class ObjWithNumberMembers {

    public short shortMember;
    public Short bigShortMember;
    public int intMember;
    public Integer bigIntMember;
    public long longMember;
    public Long bigLongMember;
    public float floatMember;
    public Float bigFloatMember;
    public double doubleMember;
    public Double bigDoubletMember;

    public short getShortMember() {
        return shortMember;
    }

    public void setShortMember(short shortMember) {
        this.shortMember = shortMember;
    }

    public Short getBigShortMember() {
        return bigShortMember;
    }

    public void setBigShortMember(Short bigShortMember) {
        this.bigShortMember = bigShortMember;
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

    public long getLongMember() {
        return longMember;
    }

    public void setLongMember(long longMember) {
        this.longMember = longMember;
    }

    public Long getBigLongMember() {
        return bigLongMember;
    }

    public void setBigLongMember(Long bigLongMember) {
        this.bigLongMember = bigLongMember;
    }

    public float getFloatMember() {
        return floatMember;
    }

    public void setFloatMember(float floatMember) {
        this.floatMember = floatMember;
    }

    public Float getBigFloatMember() {
        return bigFloatMember;
    }

    public void setBigFloatMember(Float bigFloatMember) {
        this.bigFloatMember = bigFloatMember;
    }

    public double getDoubleMember() {
        return doubleMember;
    }

    public void setDoubleMember(double doubleMember) {
        this.doubleMember = doubleMember;
    }

    public Double getBigDoubletMember() {
        return bigDoubletMember;
    }

    public void setBigDoubletMember(Double bigDoubletMember) {
        this.bigDoubletMember = bigDoubletMember;
    }

}
