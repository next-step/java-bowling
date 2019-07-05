package domain;

import java.util.Arrays;

public enum CleanScore {
    STRIKE("X", 10, true),
    SPARE("/", 10, false),
    MISS("", -1, false);

    private String symbol;
    private int score;
    private boolean isStrike;

    CleanScore(String symbol, int score, boolean isStrike) {
        this.symbol = symbol;
        this.score = score;
        this.isStrike = isStrike;
    }

    public static CleanScore valueOf(int score, boolean isStrike) {
        return Arrays.stream(values())
                .filter(result -> result.score == score && result.isStrike == isStrike)
                .findFirst()
                .orElse(MISS);
    }

    public static String valueOfSymbol(int score, boolean isStrike) {
        return valueOf(score, isStrike).getSymbol();
    }

    public String getSymbol() {
        return symbol;
    }
}
