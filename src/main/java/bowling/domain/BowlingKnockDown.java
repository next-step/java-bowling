package bowling.domain;

import bowling.exception.BowlingMaxCountException;

public class BowlingKnockDown {
    private static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 10;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String LINE = " | ";

    private String knockDownExpression;

    private int countOfBowlingKnockDown;

    public BowlingKnockDown(int count) {
        validate(count);
        this.countOfBowlingKnockDown = count;
        this.knockDownExpression = initDownExpress(count);
    }

    public BowlingKnockDown(String currentKnockDownExpression, int currentOfKnockDown, int nextOfKnockDown) {
        validate(nextOfKnockDown);
        this.countOfBowlingKnockDown = currentOfKnockDown;
        this.knockDownExpression = finalKnockDownExpress(currentKnockDownExpression, nextOfKnockDown);
    }

    private String initDownExpress(int count) {
        if (count == MIN_NUMBER) {
            return String.format(" %s ", GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format("   %s  ", STRIKE);
        }

        return String.format("  %d   ", count);
    }

    private String finalKnockDownExpress(String currentKnockDownExpression, int count) {

        if (this.countOfBowlingKnockDown + count == MAX_NUMBER) {
            return String.format("%s%s%s ", currentKnockDownExpression, LINE, SPARE);
        }

        if (count == MIN_NUMBER) {
            return String.format(" %s%s%s ", currentKnockDownExpression, LINE, GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format(" %s%s%s ", currentKnockDownExpression, LINE, STRIKE);
        }

        return String.format(" %s%s%d ", currentKnockDownExpression, LINE, count);
    }

    public String getKnockDownExpression() {
        return knockDownExpression;
    }

    public int getCountOfBowlingKnockDown() {
        return countOfBowlingKnockDown;
    }

    private static void validate(int count) {
        if (count > MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }
}
