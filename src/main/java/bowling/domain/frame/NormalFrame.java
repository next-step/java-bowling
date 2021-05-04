package bowling.domain.frame;

import bowling.domain.dto.FrameResponse;
import bowling.domain.state.PitchState;

public class NormalFrame implements Frame {
    private Round round;
    private Frame frame;

    public NormalFrame(Round round, Frame frame) {
        this.round = round;
        this.frame = frame;
    }

    @Override
    public Frame pitch(int point) {
        round = round.next(point);
        frame = getFrame();
        return frame;
    }

    private Frame getFrame() {
        if (round.isNormalMaxRound() && !round.isNext()) {
            return FinalFrame.createOf(this);
        }

        if (!round.isNext()) {
            return new NormalFrame(round.nextStart(), this);
        }

        return this;
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
    public boolean isEnd() {
        return false;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "round=" + round.getRound() +
                round.getPitchState() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;

        NormalFrame that = (NormalFrame) o;

        if (round != null ? !round.equals(that.round) : that.round != null) return false;
        return frame != null ? frame.equals(that.frame) : that.frame == null;
    }

    @Override
    public int hashCode() {
        int result = round != null ? round.hashCode() : 0;
        result = 31 * result + (frame != null ? frame.hashCode() : 0);
        return result;
    }

    @Override
    public Round getRound() {
        return round;
    }
}
