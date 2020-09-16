package bowling.domain;

public enum BowlResult {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-")
    ;

    private String expression;

    BowlResult(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
