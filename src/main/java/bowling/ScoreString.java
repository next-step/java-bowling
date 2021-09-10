package bowling;

public class ScoreString {
    private static final int EXCEPT_FIRST_DELIMITER = 1;

    private static final String GUTTER_NUMBER = "0";
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";
    private static final String GUTTER_SYMBOL = "-";

    private static final String DELIMITER = "|";

    private final String value;

    public ScoreString() {
        this.value = "";
    }

    public ScoreString(String value) {
        this.value = value;
    }

    public ScoreString append(Score score) {
        return append(score, FrameResult.MISS);
    }

    public ScoreString append(Score score, FrameResult frameResult) {
        return new ScoreString(this.value + DELIMITER + replaceToScoreString(score, frameResult));
    }

    public String getOutputString() {
        if (value.isEmpty()) {
            return value;
        }

        return value.substring(EXCEPT_FIRST_DELIMITER);
    }

    private String replaceToScoreString(Score value, FrameResult frameResult) {
        if (frameResult.equals(FrameResult.STRIKE)) {
            return STRIKE_SYMBOL;
        }

        if (frameResult.equals(FrameResult.SPARE)) {
            return SPARE_SYMBOL;
        }

        if (value.getScoreString().equals(GUTTER_NUMBER)) {
            return GUTTER_SYMBOL;
        }

        return value.getScoreString();
    }
}
