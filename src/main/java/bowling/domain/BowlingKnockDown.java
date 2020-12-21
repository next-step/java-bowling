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

    public BowlingKnockDown(int count) {
        validate(count);
        this.knockDownExpression = knockDownExpress(count);
    }

    public BowlingKnockDown(String currentBowlingKnockDown, int count) {
        validate(count);
        this.knockDownExpression = knockDownExpress(currentBowlingKnockDown, count);
    }

    public String knockDownExpress(int count) {
        if (count == MIN_NUMBER) {
            return String.format(" %s ", GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format("   %s  ", STRIKE);
        }

        return String.format("  %d   ", count);
    }

    public String knockDownExpress(String currentBowlingKnockDown, int count) {
        if (currentBowlingKnockDown.equals(GUTTER)) {
            currentBowlingKnockDown = "0";
        }

        if ((Integer.parseInt(currentBowlingKnockDown) + count) == MAX_NUMBER) {
            return String.format( "%s%s%s ", currentBowlingKnockDown, LINE, SPARE);
        }

        if (count == MIN_NUMBER) {
            return String.format(" %s%s%s ", currentBowlingKnockDown.equals("0") ? GUTTER : currentBowlingKnockDown, LINE, GUTTER);
        }

        return String.format(" %s%s%d ", currentBowlingKnockDown, LINE, count);
    }

    public String getKnockDownExpression() {
        return knockDownExpression;
    }

    private static void validate(int count) {
        if (count > MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }
}
