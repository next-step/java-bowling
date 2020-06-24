package bowling.domain.state;

public class StateExpression {

    public static final String DELIMITER = "|";

    public static final String STRIKE = "X";
    public static final String SPARE = "/";
    public static final String GUTTER = "-";
    public static final String READY = "";
    public static final String BLANK = " ";

    private StateExpression() {
    }
}
