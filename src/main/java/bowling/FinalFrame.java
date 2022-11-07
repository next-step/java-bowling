package bowling;

import java.util.List;

public class FinalFrame {

    private List<Pins> scores;

    public FinalFrame(List<Pins> scores) {
        this.scores = scores;
    }

    public boolean isFinished() {
        if (scores.size() == 2 && scores.get(1).getFalledPins() == 10) {
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

}
