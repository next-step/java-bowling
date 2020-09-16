package bowling.domain;

public class Score {
    private static final int STRIKE_OPPORTUNITY = 2;
    private static final int SPARE_OPPORTUNITY = 1;
    private static final int NO_OPPORTUNITY = 0;

    private static final int OPPORTUNITY_DECREASE_COUNT = -1;

    private int count;
    private int opportunity;

    private Score(int count, int opportunity) {
        this.count = count;
        this.opportunity = opportunity;
    }

    public static Score of(int count, int opportunity) {
        return new Score(count, opportunity);
    }

    public static Score ofStrike(int count) {
        return new Score(count, STRIKE_OPPORTUNITY);
    }

    public static Score ofSpare(int count) {
        return new Score(count, SPARE_OPPORTUNITY);
    }

    public static Score ofMiss(int count) {
        return new Score(count, NO_OPPORTUNITY);
    }

    public int toInt() {
        return count;
    }

    public boolean canNextSum() {
        return opportunity > 0;
    }

    public Score sum(Score score) {
        if (canNextSum()) {
            this.count += score.count;
            this.opportunity += OPPORTUNITY_DECREASE_COUNT;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Score{" +
                "count=" + count +
                ", opportunity=" + opportunity +
                '}';
    }
}
