package bowling.domain.frame;

import bowling.domain.state.PitchState;

public class Frame {
    private Round round;
    private Frame previousFrame;

    public Frame(Round round, Frame previousFrame) {
        this.round = round;
        this.previousFrame = previousFrame;
    }

    public Frame pitch(int point) {
        round = round.next(point);
        previousFrame = round.nextFrame(this);
        return previousFrame;
    }

    public PitchState getPitchState() {
        return round.getPitchState();
    }

    public boolean isEnd() {
        return round.isEnd();
    }

    public int getFrameRoundCount() {
        return round.getRound();
    }

    public final Frame getPreviousFrame() {
        return previousFrame;
    }

    public boolean isNormalRound() {
        return round instanceof NormalRound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;

        Frame frame = (Frame) o;

        return round != null ? round.equals(frame.round) : frame.round == null;
    }

    @Override
    public int hashCode() {
        return round != null ? round.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "round=" + round +
                ", previousFrame=" + previousFrame.getPitchState() +
                '}';
    }
}
