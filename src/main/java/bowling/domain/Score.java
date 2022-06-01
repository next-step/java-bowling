package bowling.domain;

public enum Score {
    STRIKE("X"),
    SPARE("/");

    private String symbol;

    private static final int STRIKE_SCORE = 10;

    Score(String symbol) {
        this.symbol = symbol;
    }

    public static String value(int score, boolean spare) {
        if (score == STRIKE_SCORE) {
            return STRIKE.symbol;
        }

        if (spare) {
            return SPARE.symbol;
        }

        return String.valueOf(score);
    }

    public static boolean strike(String symbol) {
        return STRIKE.symbol.equals(symbol);
    }

    public static boolean spare(String symbol) {
        return SPARE.symbol.equals(symbol);
    }
}