package bowling.domain;

public enum ScoreExpression {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-")
    ;

    private String expression;

    ScoreExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
