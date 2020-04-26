package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean isLast;

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
        shot(shotScores.getNext(shot));
    }

    private void shot(ShotScore shot) {
        if (isShootLimitedSize()) {
            throw new IllegalStateException(String.format("shot Frame fail. cannot shot over %d times", getShotLimit()));
        }

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
        return isFrameSet() && shotScores.isScoreCalculated();
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

    public int getFrameScore() {
        return score();
    }

    private int score() {
        if (isLast) {
            return shotScores.singleScore();
        }
        return shotScores.totalScore();
    }

    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }
}
