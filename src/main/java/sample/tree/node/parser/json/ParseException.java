package sample.tree.node.parser.json;

/**
 * Created by kopelevi on 20/09/2015.
 */
public class ParseException extends RuntimeException{

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
