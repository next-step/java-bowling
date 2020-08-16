package bowling.domian.frame;

public class Score {
    private int score;
    private int addableCount;

    private Score(int falledPinsCount) {
        this(falledPinsCount, 0);
    }

    private Score(int falledPinsCount, int addableCount) {
        this.score = falledPinsCount;
        this.addableCount = addableCount;
    }

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score miss(int falledPinsCount) {
        return new Score(falledPinsCount);
    }

    public boolean isCalculateDone() {
        return addableCount == 0;
    }

    public int getScore() {
        return score;
    }

    public Score additionalBowl(int falledPinsCount) {
        return new Score(this.score + falledPinsCount, this.addableCount - 1);
    }
}
