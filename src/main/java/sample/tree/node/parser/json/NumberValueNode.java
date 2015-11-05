package sample.tree.node.parser.json;

import java.security.InvalidParameterException;

/**
 * Created by kopelevi on 17/09/2015.
 */
public class NumberValueNode extends SimpleValueNode {

    public NumberValueNode(String json) {
        try {
            if (json.contains(".")) {
                Double.parseDouble(json);
            } else {
                Long.parseLong(json);
            }
            super.value = json;
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Giving param cannot be parsed to a number, given:" + json);
        }
    }

    public NumberValueNode(Double number) {
        if ((number == Math.floor(number)) && !Double.isInfinite(number)) {
            super.value = String.valueOf(number.intValue());// number but int
        } else {
            super.value = String.valueOf(number);// number but double
        }

    }

}
