package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pins;

public class FinalFrame extends Frame {

    private static final int ONCE = 1;
    private static final int TWICE = 2;

    private Score score;
    private FinalPins finalPins;

    public FinalFrame() {
        score = new Score();
        finalPins = new FinalPins();
    }

    @Override
    public void addPoint(int bonusPoint) {
        score.addScore(bonusPoint);
    }

    @Override
    public void bowl(int pin) {
        finalPins.bowl(pin);
        score.addScore(pin);
    }

    @Override
    public boolean isEnd() {
        return finalPins.isEnd();
    }

    @Override
    public int bonusAmount() {
        return NO_BONUS;
    }

    @Override
    public void endScoring() {
        if (isEnd()) {
            score.endScoring();
        }
    }

    @Override
    public boolean endedScoring() {
        return score.endedScoring();
    }

    @Override
    public Pins pins() {
        return finalPins;
    }

    @Override
    public Score score() {
        return score;
    }
}
