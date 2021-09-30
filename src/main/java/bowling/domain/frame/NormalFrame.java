package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final int round;
    private final Score score;
    private Frame frame;

    public NormalFrame(int round, Score score, Frame frame) {
        this.round = round;
        this.score = score;
        this.frame = frame;
    }

    static Frame of(int round, Score score, Frame frame) {
        return new NormalFrame(round, score, frame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(1, NormalScore.emptyScore(), null);
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public boolean isScoreNextStorable() {
        return false;
    }

    @Override
    public void updateScorePin() {

    }

    @Override
    public Frame createNextFrame() {
        return null;
    }

    @Override
    public Frame nextFrame() {
        return null;
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
            .equals(frame, that.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, score, frame);
    }

}
