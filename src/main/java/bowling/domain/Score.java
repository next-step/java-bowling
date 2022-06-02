package bowling.domain;

public class Score {
    private static final int MAX_PINS = 10;
    private final int score;
    private final int remainNumber;

    public Score(int score, int remainNumber) {
        this.score = score;
        this.remainNumber = remainNumber;
    }

    public boolean isCalculatable() {
        return remainNumber == 0;
    }

    public Score bowl(int pins) {
        return new Score(this.score + pins , this.remainNumber -1);
    }

    public static Score ofStrike() {
        return new Score(MAX_PINS, 2);
    }

    public int getScore() {
        return score;
    }
}
