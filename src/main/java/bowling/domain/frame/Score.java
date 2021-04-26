package bowling.domain.frame;

public enum Score {
    GUTTER("-", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    STRIKE("X", 10),
    SPARE("/", 10);

    private String expression;
    private int score;

    Score(String expression, int score) {
        this.expression = expression;
        this.score = score;
    }

    public static Score valueOf(int score) {
        return null;
    }
}
