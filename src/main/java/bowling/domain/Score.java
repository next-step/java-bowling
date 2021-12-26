package bowling.domain;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int pinCount, int newPinCount) {
        return Score.ofMiss(new KnockedPinCount(pinCount), new KnockedPinCount(newPinCount));
    }

    public static Score ofMiss(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        return new Score(pinCount.value() + newPinCount.value(), 0);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofFirst(int pinCount) {
        return Score.ofFirst(new KnockedPinCount(pinCount));
    }

    public static Score ofFirst(KnockedPinCount pinCount) {
        return new Score(pinCount.value(), 0);
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public int getScore() {
        return score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }
}
