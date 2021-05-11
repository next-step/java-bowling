package bowling.domain.frame;

import bowling.domain.state.PitchState;
import bowling.domain.state.Start;

public class NormalRound extends Round {
    private int round;
    private PitchState pitchState;

    public NormalRound(int round, PitchState pitchState) {
        this.round = round;
        this.pitchState = pitchState;
    }

    public static Round createOf() {
        return new NormalRound(1, Start.createOf());
    }

    public Round next(int point) {
        pitchState = pitchState.next(point);
        return new NormalRound(round, pitchState);
    }

    @Override
    public Frame nextFrame(Frame frame) {
        if (!pitchState.isNext() && isMaxRound()) {
            return new Frame(FinalRound.createOf(), frame);
        }

        if (!pitchState.isNext()) {
            return new Frame(nextStartRound(), frame);
        }

        return frame;
    }

    @Override
    public boolean isMaxRound() {
        return round == (MAX_FRAME - MIN_FRAME);
    }

    private Round nextStartRound() {
        return new NormalRound(round + 1, Start.createOf());
    }

    @Override
    public PitchState getPitchState() {
        return pitchState;
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalRound)) return false;

        NormalRound that = (NormalRound) o;

        if (round != that.round) return false;
        return pitchState != null ? pitchState.equals(that.pitchState) : that.pitchState == null;
    }

    @Override
    public int hashCode() {
        int result = round;
        result = 31 * result + (pitchState != null ? pitchState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NormalRound{" +
                "round=" + round +
                ", pitchState=" + pitchState +
                '}';
    }
}
