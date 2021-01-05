package bowling.domain.score;

import bowling.domain.frame.DownedPin;

import java.util.ArrayList;
import java.util.List;

public class Score {

    private final List<DownedPin> framePins;
    private final List<DownedPin> additionalPins;

    public Score(List<DownedPin> framePins) {
        this.framePins = framePins;
        this.additionalPins = new ArrayList<>();
    }

    public void record(DownedPin additionalPins) {
    }
}
