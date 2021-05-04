package bowling.domain.frame;

import bowling.domain.state.PitchState;
import bowling.domain.state.Start;
import bowling.domain.view.ResultView;

public class Round {
    public static int FINAL_MAX_COUNT = 3;

    private int round;
    private PitchState pitchState;

    public Round(int round, PitchState pitchState) {
        this.round = round;
        this.pitchState = pitchState;
    }

    public static Round createOf() {
        return new Round(ResultView.MIN_FRAME, new Start());
    }

    public static Round createOf(int round) {
        return new Round(round, new Start());
    }

    public Round next(int point) {
        pitchState = pitchState.next(point);
        return new Round(round, pitchState);
    }

    public Round nextStart() {
        return new Round(round + 1, new Start());
    }

    public Round next() {
        return new Round(round + 1, pitchState);
    }

    public boolean isNormalMaxRound() {
        return round == ResultView.MAX_FRAME - 1;
    }

    public boolean isFinalMaxRound() {
        if (!isNext() && isFinal()) {
            return true;
        }

        return round > FINAL_MAX_COUNT ;
    }

    public boolean isNext() {
        return pitchState.isNext();
    }

    public boolean isFinal() {
        return pitchState.isFinal();
    }

    public int getRound() {
        return round;
    }

    public PitchState getPitchState() {
        return pitchState;
    }

    @Override
    public String toString() {
        return "Round{" +
                "round=" + round +
                ", pitchState=" + pitchState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round)) return false;

        Round round1 = (Round) o;

        if (round != round1.round) return false;
        return pitchState != null ? pitchState.equals(round1.pitchState) : round1.pitchState == null;
    }

    @Override
    public int hashCode() {
        int result = round;
        result = 31 * result + (pitchState != null ? pitchState.hashCode() : 0);
        return result;
    }
}
