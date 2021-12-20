package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Pitch;
import bowling.domain.frame.Frame;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;

import java.util.Objects;

public class Progress implements State {
    private final Pins beforePins;
    private final boolean retry;

    public Progress(Pins beforePins) {
        this(beforePins, false);
    }

    public Progress(Pins beforePins, boolean retry) {
        this.beforePins = beforePins;
        this.retry = retry;
    }

    /**
     * Final Frame
     * strike -> strike -> strike
     *                  -> miss
     *        -> miss -> spare
     *                -> miss
     * miss -> spare -> strike
     *               -> miss
     *      -> miss
     *
     * Normal Frame
     * strike
     * miss -> spare
     *      -> miss
     */
    @Override
    public State run(Pitch pitch, Frame frame) {
        Pins pins = pitch.run();
        frame.addPitch(pitch);
        if (frame.isFinal()) {
            return checkFinalState(frame, pins);
        }
        return checkNormalState(pins);
    }

    private State checkFinalState(Frame frame, Pins pins) {
        if (beforePins.isStrike(pins)) {
            return checkThirdPitchNoOfStrike(frame, pins);
        }
        return checkRetryOfMissAndSpare(frame, pins);
    }

    private State checkThirdPitchNoOfStrike(Frame frame, Pins pins) {
        if (frame.isThirdPitch()) {
            return Strike.from();
        }
        return new Progress(pins);
    }

    private State checkRetryOfMissAndSpare(Frame frame, Pins pins) {
        if (beforePins.isSpare(pins)) {
            return checkThirdPitchNoOfSpare(frame, pins);
        }
        return checkThirdPitchNoOfMiss(frame, pins);
    }

    private State checkThirdPitchNoOfSpare(Frame frame, Pins pins) {
        if (frame.isThirdPitch()) {
            return Spare.of(beforePins, pins);
        }
        return new Progress(pins);
    }

    private State checkThirdPitchNoOfMiss(Frame frame, Pins pins) {
        if (frame.isThirdPitch()) {
            return checkCreateMiss(pins);
        }
        return checkRetryMiss(pins);
    }

    private State checkCreateMiss(Pins pins) {
        if (beforePins.isStrike() && pins.isMiss()) {
            return Miss.from(pins);
        }
        return checkRetryMiss(pins);
    }

    private State checkRetryMiss(Pins pins) {
        if (retry) {
            return new Progress(pins);
        }
        return Miss.from(beforePins, pins);
    }

    private State checkNormalState(Pins pins) {
        if (beforePins.isSpare(pins)) {
            return Spare.of(beforePins, pins);
        }
        return Miss.from(beforePins, pins);
    }

    @Override
    public boolean progressing() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return retry == progress.retry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(retry);
    }

    @Override
    public String toString() {
        return "Progress{" +
                "retry=" + retry +
                '}';
    }
}
