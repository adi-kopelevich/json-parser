package sample.tree.node.parser.json;

import sample.tree.node.parser.json.annotaions.JsonObjectAnnotation;

/**
 * Created by kopelevi on 20/11/2015.
 */
@JsonObjectAnnotation
public class ObjWithArrayMembers {
    public short[] shortArray;
//    public int[] intArray;
//    public long[] longArray;
//    public float[] floatArray;
//    public double[] doubleArray;
    public Short[] bigShortArray;
//    public Integer[] bifIntArray;
//    public Long[] bigLongArray;
//    public Float[] bigFloatArray;
//    public Double[] bigDoubleArray;
    //    public boolean[] booleanArray;
    //    public Boolean[] bigBooleanArray;
//    public String[] StringArray;

    public short[] getShortArray() {
        return shortArray;
    }

    public void setShortArray(short[] shortArray) {
        this.shortArray = shortArray;
    }

    public Short[] getBigShortArray() {
        return bigShortArray;
    }

    public void setBigShortArray(Short[] bigShortArray) {
        this.bigShortArray = bigShortArray;
    }
}
