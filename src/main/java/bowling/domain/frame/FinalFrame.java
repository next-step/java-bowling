package bowling.domain.frame;

import bowling.domain.dto.FrameResponse;
import bowling.domain.state.PitchState;

public class FinalFrame implements Frame {
    private Round round;
    private Frame frame;

    public FinalFrame(Round round, Frame frame) {
        this.round = round;
        this.frame = frame;
    }

    public static FinalFrame createOf(Frame frame) {
        return new FinalFrame(Round.createOf(), frame);
    }

    @Override
    public Frame pitch(int point) {
        round = round.next(point);
        frame = getFrame();
        return frame;
    }

    private Frame getFrame() {
        if (!round.isNext() && !round.isFinal()) {
            return new FinalFrame(round.nextStart(), this);
        }

        return new FinalFrame(round.next(), this);
    }

    @Override
    public boolean isEnd() {
        return round.isFinalMaxRound();
    }

    @Override
    public PitchState getPitchState() {
        return round.getPitchState();
    }

    @Override
    public FrameResponse getFrameResponse() {
        return new FrameResponse(frame);
    }

    @Override
    public Round getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "round=" + round.getRound() +
                round.getPitchState() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;

        FinalFrame that = (FinalFrame) o;

        if (round != null ? !round.equals(that.round) : that.round != null) return false;
        return frame != null ? frame.equals(that.frame) : that.frame == null;
    }

    @Override
    public int hashCode() {
        int result = round != null ? round.hashCode() : 0;
        result = 31 * result + (frame != null ? frame.hashCode() : 0);
        return result;
    }
}
