package bowling;

import java.util.List;

public class FinalFrame implements Frame {

    private List<Pins> scores;

    public FinalFrame(List<Pins> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isFinished() {
        if (scores.size() == 2 && scores.get(1).getFalledPins() != 10) {
            return true;
        }
        if (scores.size() == 3) {
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
    public int firstScore() {
        return scores.get(0).getFalledPins();
    }

    @Override
    public int secondScore() {
        return scores.get(1).getFalledPins();
    }
}
