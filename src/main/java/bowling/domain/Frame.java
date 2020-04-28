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

    static Frame init() {
        return new Frame(new ArrayList<>(), false);
    }

    Frame next(int shot) {
        Frame nextFrame = new Frame(new ArrayList<>(), false);
        nextFrame.shot(shotScores.getNext(shot));
        return nextFrame;
    }

    Frame last(int shot) {
        Frame nextFrame = new Frame(new ArrayList<>(), true);
        nextFrame.shot(shotScores.getNext(shot));
        return nextFrame;
    }

    void shot(int shot) {
        if (isScoreCalculated()) {
            throw new IllegalStateException(String.format("shot Frame fail. cannot shot over %d times", getShotLimit()));
        }

        if (isFrameSet()) {
            frameScore.addBonus(shot);
            return;
        }

        shot(shotScores.getNext(shot));
    }

    private void shot(ShotScore shot) {
        shotScores.add(shot);
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
            this.frameScore = frameScore != null ? frameScore : shotScores.getCalculateScore();
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
        if(!isFrameSet()){
            return DefaultFrameScore.NULL;
        }
        return frameScore != null ? frameScore : shotScores.getCalculateScore();
    }

    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }
}
