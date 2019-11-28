package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;

public class Spare extends EndState {
    public Spare(int first, int second) {
        this(Arrays.asList(Pin.of(first), Pin.of(second)));
    }

    public Spare(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofSpare(totalScore);
    }
}
