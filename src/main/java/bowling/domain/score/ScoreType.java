package bowling.domain.score;

public enum ScoreType {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    ORDINARY("");

    private final String expression;

    ScoreType(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
