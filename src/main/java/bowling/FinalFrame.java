package bowling;

import java.util.List;

public class FinalFrame implements Frame {

    private List<Pins> scores;
    private Thrown thrown;
    private Pins bonusPins;

    // TODO
    public FinalFrame(List<Pins> scores) {
        this.scores = scores;
    }

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

    public List<Pins> getValues() {
        return scores;
    }

    @Override
    public Frame bowl(int countOfPins) {
        Pins totalPins = scores.get(0).totalPins(countOfPins);
        scores.add(totalPins);
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
}
