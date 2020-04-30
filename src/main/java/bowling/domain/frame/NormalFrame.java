package bowling.domain.frame;

import bowling.domain.ShotScore;
import bowling.domain.ShotScores;
import bowling.domain.frameScore.DefaultFrameScore;
import bowling.domain.frameScore.FrameScore;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private FrameScore frameScore;

    private NormalFrame(ShotScores shotScores) {
        this.shotScores = shotScores;
    }

    public static NormalFrame init() {
        return new NormalFrame(ShotScores.of());
    }

    public Frame next() {
        return new NormalFrame(ShotScores.of());
    }

    public Frame last() {
        return FinalFrame.of();
    }

    public void shot(int shot) {
        if (isFrameSet()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        addShotScore(shot);
    }

    private void addShotScore(int shot) {
        ShotScore nextScore = shotScores.getNext(shot);
        shotScores.add(nextScore);

        if (nextScore.scoreType().isFinished()) {
            setFrameScore();
        }
    }

    private boolean isFrameScoreSet() {
        return frameScore != null;
    }

    private void setFrameScore() {
        this.frameScore = isFrameScoreSet() ? frameScore : DefaultFrameScore.of(shotScores.totalScore(), shotScores.getLastType().getBonusCount());
    }

    public boolean isFrameSet() {
        return shotScores.hasSize(SHOT_LIMIT) ||
                shotScores.hasClear();
    }

    public FrameScore getFrameScore() {
        if (!isFrameSet()) {
            return DefaultFrameScore.NULL;
        }
        setFrameScore();
        return frameScore;
    }

    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "shotScores=" + shotScores +
                ", frameScore=" + frameScore +
                '}';
    }
}
