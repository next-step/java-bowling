package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final int round;
    private final Score score;
    private Frame nextFrame;

    public NormalFrame(int round, Score score, Frame nextFrame) {
        this.round = round;
        this.score = score;
        this.nextFrame = nextFrame;
    }

    static Frame of(int round, Score score, Frame nextFrame) {
        return new NormalFrame(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(1, NormalScore.emptyScore(), null);
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public boolean isScoreNextStorable() {
        return score.isNextStorable();
    }

    @Override
    public void updateScorePin() {

    }

    @Override
    public Frame createNextFrame() {
        return this.nextFrame = NormalFrame.of(round + 1, NormalScore.emptyScore(), null);
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return round == that.round && Objects.equals(score, that.score) && Objects
            .equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, score, nextFrame);
    }

}
