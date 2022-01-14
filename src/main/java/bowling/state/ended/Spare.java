package bowling.state.ended;

import bowling.Pins;
import bowling.frame.Score;

public class Spare extends Ended {

    private final Pins beforePins;
    private final Pins afterPins;

    public Spare(Pins beforePins, Pins afterPins) {
        if (beforePins.totalPinCount(afterPins) != Pins.MAX_PINS_COUNT) {
            throw new IllegalArgumentException(String.format("스페어는 %d개를 쓰러트려야 합니다.", Pins.MAX_PINS_COUNT));
        }
        this.beforePins = beforePins;
        this.afterPins = afterPins;
    }

    @Override
    public String symbol() {
        return beforePins.symbol() + "|/";
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = this.beforePins.sumScore(beforeScore);
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        return afterPins.sumScore(beforeScore);
    }
}
