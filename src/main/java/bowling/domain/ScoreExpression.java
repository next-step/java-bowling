package bowling.domain;

public enum ScoreExpression {
    STRIKE("X", 2),
    SPARE("/", 1),
    GUTTER("-", 0),
    MISS("", 0)
    ;

    private String expression;

    ScoreExpression(String expression, int bonusScoreCount) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
