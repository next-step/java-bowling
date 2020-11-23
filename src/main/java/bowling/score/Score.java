package bowling.score;

import static bowling.score.Pin.PINS_MAX_VALUE;

public class Score {

    private static final int OPPORTUNITY_STRIKE = 2;
    private static final int OPPORTUNITY_SPARE = 1;
    private static final int OPPORTUNITY_CLOSE = 0;
    private static final int DECREASE_OPPORTUNITY = -1;

    private int fellPins;
    private int leftOpportunity;

    private Score(int fellPins, int leftOpportunity) {
        this.fellPins = fellPins;
        this.leftOpportunity = leftOpportunity;
    }

    public static Score of(int score) {
        return new Score(score, OPPORTUNITY_CLOSE);
    }

    public static Score ofStrike() {
        return new Score(PINS_MAX_VALUE, OPPORTUNITY_STRIKE);
    }

    public static Score ofSpare() {
        return new Score(PINS_MAX_VALUE, OPPORTUNITY_SPARE);
    }

    public static Score ofMiss(int score) {
        return new Score(score, OPPORTUNITY_CLOSE);
    }

    public Score calculate(Score score) {
        if (!score.isCalculateScore()) {
            score.fellPins += this.fellPins;
            score.leftOpportunity += DECREASE_OPPORTUNITY;
        }
        return score;
    }

    public boolean isCalculateScore() {
        return leftOpportunity == OPPORTUNITY_CLOSE;
    }

    public int getFellPins() {
        return fellPins;
    }

    public int getLeftOpportunity() {
        return leftOpportunity;
    }
}
