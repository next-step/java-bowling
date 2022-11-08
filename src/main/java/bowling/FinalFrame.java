package bowling;

import java.util.List;

public class FinalFrame implements Frame {

    private Thrown thrown;
    private Pins bonusPins;

    private FinalFrame(Thrown thrown) {
        this.thrown = thrown;
    }

    public static FinalFrame of(int countOfPins) {
        return new FinalFrame(new Thrown(Pins.from(countOfPins)));
    }

    @Override
    public boolean isFinished() {
        if (bonusPins.getFalledPins() > 0) {
            return true;
        }

        if (!thrown.isSpare()) {
            return true;
        }

        return false;
    }

    @Override
    public Frame bowl(int countOfPins) {
        int totalScore = thrown.getScore() + countOfPins;
        bonusPins = Pins.from(totalScore);
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public int getScore() {
        return thrown.getScore() + bonusPins.getFalledPins();
    }

    @Override
    public int firstPins() {
        return thrown.firstPins();
    }

    @Override
    public int secondPins() {
        return thrown.secondPins();
    }

    @Override
    public boolean hasTurn() {
        return thrown.hasTurn();
    }
}
