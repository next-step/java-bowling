package bowling.domain;

public class Score {
    private static final int TEN_SCORE = 10;
    private static final int STRIKE_LEFT = 2;
    private static final int SPARE_LEFT = 1;
    private static final int NORMAL_LEFT = 0;
    private static final int BOWL_COUNT = 1;
    private static final int ZERO = 0;

    private int score;
    private final int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int pinCount, int newPinCount) {
        return Score.ofMiss(new KnockedPinCount(pinCount), new KnockedPinCount(newPinCount));
    }

    public static Score ofMiss(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        return new Score(pinCount.value() + newPinCount.value(), ZERO);
    }

    public static Score ofStrike() {
        return new Score(TEN_SCORE, STRIKE_LEFT);
    }

    public static Score ofSpare() {
        return new Score(TEN_SCORE, SPARE_LEFT);
    }

    public static Score ofFirst(int pinCount) {
        return Score.ofFirst(new KnockedPinCount(pinCount));
    }

    public static Score ofFirst(KnockedPinCount pinCount) {
        return new Score(pinCount.value(), NORMAL_LEFT);
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - BOWL_COUNT);
    }

    public int getScore() {
        return score;
    }

    public boolean canCalculateScore() {
        return left == NORMAL_LEFT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        if (score != score1.score) return false;
        return left == score1.left;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + left;
        return result;
    }
}
