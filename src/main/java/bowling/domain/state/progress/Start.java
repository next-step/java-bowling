package bowling.domain.state.progress;

import bowling.domain.pin.Pins;
import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.end.Strike;

public class Start extends Progress {

    @Override
    public State run(Pitch pitch) {
        Pins pins = pitch.run();
        if (pins.isStrike()) {
            return Strike.from();
        }
        return FirstHit.from(pins);
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore;
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
