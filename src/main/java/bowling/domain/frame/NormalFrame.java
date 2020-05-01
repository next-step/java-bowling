package bowling.domain.frame;

import bowling.domain.Shots;
import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.Shot;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int SHOT_LIMIT = 2;

    private final Shots shots;
    private FrameScore frameScore;

    private NormalFrame(Shots shots) {
        this.shots = shots;
    }

    public static NormalFrame init() {
        return new NormalFrame(Shots.of());
    }

    @Override
    public Frame next() {
        return new NormalFrame(Shots.of());
    }

    @Override
    public Frame last() {
        return FinalFrame.of();
    }

    @Override
    public void shot(int shot) {
        if (isFrameSet()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        addShotScore(shot);
    }

    private void addShotScore(int shot) {
        shots.add(shot);
    }

    @Override
    public boolean isFrameSet() {
        return shots.hasSize(SHOT_LIMIT) ||
                shots.hasClear();
    }

    @Override
    public FrameScore getFrameScore() {
        if (!isFrameSet()) {
            return DefaultFrameScore.NULL;
        }
        return setAndGetFrameScore();
    }

    private FrameScore setAndGetFrameScore() {
        frameScore = frameScore != null ? frameScore : DefaultFrameScore.of(shots.totalScore(), shots.getLastType().getBonusCount());
        return frameScore;
    }

    @Override
    public List<Shot> shots() {
        return shots.shots();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "shotScores=" + shots +
                '}';
    }
}
