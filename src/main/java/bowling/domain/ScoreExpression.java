package bowling.domain;

public enum ScoreExpression {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS("")
    ;

    private String expression;

    ScoreExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
