package bowling.domain.frame;

import bowling.domain.shot.Shot;
import bowling.domain.Shots;
import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int SHOT_LIMIT = 3;

    private final Shots shots;

    private FinalFrame(Shots shots) {
        this.shots = shots;
    }

    public static FinalFrame of() {
        return new FinalFrame(Shots.of());
    }

    @Override
    public Frame last() {
        throw new UnsupportedOperationException("FinalFrame can not get last");
    }

    @Override
    public Frame next() {
        throw new UnsupportedOperationException("FinalFrame can not get next");
    }

    @Override
    public boolean isFrameSet() {
        return shots.hasSize(SHOT_LIMIT) ||
                (shots.hasSize(2) && !shots.hasClear());
    }

    @Override
    public List<Shot> shotScores() {
        return shots.shotScores();
    }

    @Override
    public FrameScore getFrameScore() {
        if(!isFrameSet()){
            return DefaultFrameScore.NULL;
        }
        return DefaultFrameScore.of(shots.totalScore(),0);
    }

    @Override
    public void shot(int shot) {
        if (isFrameSet()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        shots.add(shot);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "shotScores=" + shots +
                '}';
    }
}
