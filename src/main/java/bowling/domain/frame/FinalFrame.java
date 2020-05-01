package bowling.domain.frame;

import bowling.domain.Shots;
import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.Shot;

import java.util.List;

public class FinalFrame implements Frame {
    public static final int FRAME_NUMBER = 10;

    private static final int SHOT_LIMIT = 3;
    private static final int NOT_HAVE_BONUS_SHOT_LIMIT = 2;

    private final Shots shots;

    private FinalFrame(Shots shots) {
        this.shots = shots;
    }

    public static FinalFrame of() {
        return new FinalFrame(Shots.of());
    }

    @Override
    public Frame next() {
        throw new UnsupportedOperationException("FinalFrame can not get next");
    }

    @Override
    public boolean isFrameSet() {
        return shots.hasSize(SHOT_LIMIT) ||
                (shots.hasSize(NOT_HAVE_BONUS_SHOT_LIMIT) && !shots.hasClear());
    }

    @Override
    public List<Shot> shots() {
        return shots.shots();
    }

    @Override
    public FrameScore getFrameScore() {
        if (!isFrameSet()) {
            return DefaultFrameScore.NULL;
        }
        return DefaultFrameScore.of(shots.totalScore(), 0);
    }

    @Override
    public void shot(int shot) {
        if (isFrameSet()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        shots.add(shot);
    }

    @Override
    public int getFrameNumber() {
        return FRAME_NUMBER;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "shots=" + shots +
                '}';
    }
}
