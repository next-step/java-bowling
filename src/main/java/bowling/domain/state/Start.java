package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Pitch;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Strike;

public class Start implements State {

    @Override
    public State run(Pitch pitch, Frame frame) {
        Pins pins = pitch.run();
        frame.addPitch(pitch);
        if (pins.isStrike()) {
            return checkFrameOfStrike(frame, pins);
        }
        return checkFrameOfMiss(frame, pins);
    }

    private State checkFrameOfStrike(Frame frame, Pins pins) {
        if (frame.isFinal()) {
            frame.addState(Strike.from());
            return new Progress(pins, true);
        }
        return Strike.from();
    }

    private State checkFrameOfMiss(Frame frame, Pins pins) {
        if (frame.isFinal()) {
            frame.addState(Miss.from(pins));
            return new Progress(pins, false);
        }
        return new Progress(pins);
    }

    @Override
    public boolean progressing() {
        return true;
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String symbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
