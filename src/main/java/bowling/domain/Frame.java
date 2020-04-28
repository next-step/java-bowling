package bowling.domain;

import bowling.domain.frameScore.DefaultFrameScore;
import bowling.domain.frameScore.FrameScore;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean isLast;
    private DefaultFrameScore frameScore;

    private Frame(List<ShotScore> shotScores, boolean isLast) {
        this.shotScores = ShotScores.of(shotScores);
        this.isLast = isLast;
    }

    static Frame normalFrame() {
        return new Frame(new ArrayList<>(), false);
    }

    static Frame lastFrame() {
        return new Frame(new ArrayList<>(), true);
    }

    void shot(int shot) {
        if (isScoreCalculated()) {
            throw new IllegalStateException(String.format("shot Frame fail. cannot shot over %d times", getShotLimit()));
        }

        if (isFrameScoreSet()) {
            frameScore.addBonus(shot);
        }

        if (!isFrameSet()) {
            addShotScore(shot);
        }
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
        this.frameScore = isFrameScoreSet() ? frameScore : shotScores.getCalculateScore();
    }

    boolean isFrameSet() {
        return isShootLimitedSize() ||
                cannotShooting();
    }

    private boolean isShootLimitedSize() {
        return shotScores.hasSize(getShotLimit());
    }

    public boolean isScoreCalculated() {
        if (isLast) {
            return isFrameSet();
        }

        if (isFrameSet()) {
            setFrameScore();
            return frameScore.isCalculated();
        }
        return false;
    }

    private int getShotLimit() {
        return isLast ? SHOT_LIMIT + 1 : SHOT_LIMIT;
    }

    private boolean cannotShooting() {
        if (isLast) {
            return shotScores.hasSize(SHOT_LIMIT) && !shotScores.hasClear();
        }
        return shotScores.hasClear();
    }

    public FrameScore getFrameScore() {
        if (!isFrameSet()) {
            return DefaultFrameScore.NULL;
        }
        return isFrameScoreSet() ? frameScore : shotScores.getCalculateScore();
    }

    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }
}
