package bowling.domain.frame.score;

public enum FrameScore {
    STRIKE("X "),
    SPARE("/"),
    MISS("  "),
    GUTTER("-"),
    RUNNING("")
    ;

    private String expression;

    FrameScore(String expression) {
        this.expression = expression;
    }

    public String expression() {
        return expression;
    }
}
