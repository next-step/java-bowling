package bowling.model;

import bowling.model.frame.FrameNumber;
import bowling.model.frame.Frames;
import bowling.utility.Assert;

import java.util.Objects;

public final class Bowling {

    private final Participant participant;
    private final Frames frames;

    private Bowling(Participant participant, Frames frames) {
        Assert.notNull(participant, "participant must not be null");
        Assert.notNull(frames, "frames must not be null");
        this.participant = participant;
        this.frames = frames;
    }

    public static Bowling from(Participant participant) {
        return new Bowling(participant, Frames.init());
    }

    public static Bowling of(Participant participant, Frames frames) {
        return new Bowling(participant, frames);
    }

    public Bowling pitch(Pins countOfHit) {
        validateState();
        return of(participant, frames.bowling(countOfHit));
    }

    public Participant participant() {
        return participant;
    }

    boolean isFinished() {
        return frames.isFinished();
    }

    public Frames frames() {
        return frames;
    }

    public boolean isNextFrameNumberGreaterThan(Bowling bowling) {
        return frames.nextNumberGreaterThan(bowling.frames);
    }

    private void validateState() {
        if (isFinished()) {
            throw new IllegalStateException(String.format("bowling(%s) is already finished", this));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant, frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bowling bowling = (Bowling) o;
        return Objects.equals(participant, bowling.participant) && Objects.equals(frames, bowling.frames);
    }

    @Override
    public String toString() {
        return "Bowling{" +
                "participant=" + participant +
                ", frames=" + frames +
                '}';
    }
}
