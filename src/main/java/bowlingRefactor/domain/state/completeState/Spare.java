package bowlingRefactor.domain.state.completeState;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.Score;

import java.util.Arrays;
import java.util.List;

public class Spare extends Complete {

    public Spare(int first, int second) {
        this.pins = Arrays.asList(Pin.of(first), Pin.of(second));
    }

    public Spare(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofSpare(totalScore);
    }
}
