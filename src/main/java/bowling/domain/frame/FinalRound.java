package bowling.domain.frame;

import bowling.domain.state.PitchState;
import bowling.domain.state.Start;

public class FinalRound extends Round {
    private int count;
    private PitchState pitchState;


    public FinalRound(int count, PitchState pitchState) {
        this.count = count;
        this.pitchState = pitchState;
    }

    public static Round createOf() {
        return new FinalRound(1, Start.createOf());
    }

    public Round next(int point) {
        pitchState = pitchState.next(point);
        return new FinalRound(count, pitchState);
    }

    @Override
    public Frame nextFrame(Frame frame) {
        if (!pitchState.isNext() && !pitchState.isFinal()) {
            return new Frame(nextStartRound(), frame);
        }

        return new Frame(nextRound(), frame);
    }

    @Override
    public boolean isMaxRound() {
        if (!pitchState.isNext() && pitchState.isFinal()) {
            return true;
        }
        return count > FINAL_MAX_COUNT;
    }

    private Round nextStartRound() {
        return new FinalRound(count + 1, Start.createOf());
    }

    private Round nextRound() {
        return new FinalRound(count + 1, pitchState);
    }

    @Override
    public PitchState getPitchState() {
        return pitchState;
    }

    @Override
    public int getRound() {
        return MAX_FRAME;
    }

    @Override
    public boolean isEnd() {
        if (!pitchState.isNext() && pitchState.isFinal()) {
            return true;
        }
        return count > FINAL_MAX_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalRound)) return false;

        FinalRound that = (FinalRound) o;

        if (count != that.count) return false;
        return pitchState != null ? pitchState.equals(that.pitchState) : that.pitchState == null;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (pitchState != null ? pitchState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FinalRound{" +
                "count=" + count +
                ", pitchState=" + pitchState +
                '}';
    }
}
