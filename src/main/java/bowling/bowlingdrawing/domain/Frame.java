package bowling.bowlingdrawing.domain;

import java.util.Objects;

public class Frame {

    private final Pitchings pitchings;
    private Frame nextFrame;

    private Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
    }

    public static Frame of(Pitchings pitchings) {
        return new Frame(pitchings);
    }

    public static Frame of(int firstPitchingPins, int secondPitchingPins) {
        return of(new Pitchings(firstPitchingPins, secondPitchingPins));
    }

    public static Frame of(int firstPitchingPins) {
        return of(new Pitchings(firstPitchingPins));
    }

    public static Frame first(int firstPitchingPins) {
        return of(firstPitchingPins);
    }

    public Frame next(int firstPitchingPins) {
        this.nextFrame = of(firstPitchingPins);
        return this.nextFrame;
    }

    public void pitching(int pins) {
        pitchings.pitching(new Pitching(pins));
    }

    public boolean done() {
        return pitchings.done();
    }

    public int frameScore() {
        return pitchings.score();
    }

    private int firstPitchingScore() {
        return pitchings.firstPitchingScore();
    }

    public int score() {
        if (pitchings.strike()) {
            return pitchings.score() + nextFrame.frameScore();
        }

        if (pitchings.spare()) {
            return pitchings.score() + nextFrame.firstPitchingScore();
        }

        return pitchings.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;
        Frame frame = (Frame) o;
        return Objects.equals(pitchings, frame.pitchings) && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchings, nextFrame);
    }
}
