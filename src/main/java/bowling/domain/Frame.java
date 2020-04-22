package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean isLast;

    protected Frame(List<ShotScore> shotScores, boolean isLast) {
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
        int shotLimit = getShotLimit();
        if (shotScores.isSize(shotLimit)) {
            throw new IllegalStateException(String.format("shot Frame fail. cannot shot over %d times", shotLimit));
        }

        shotScores.add(shot);
    }

    boolean isFrameSet() {
        return shotScores.isSize(getShotLimit()) ||
                (cannotShooting());
    }

    private int getShotLimit() {
        return isLast ? SHOT_LIMIT + 1 : SHOT_LIMIT;
    }

    private boolean cannotShooting() {
        if (isLast) {
            return shotScores.isSize(SHOT_LIMIT) && !shotScores.hasClear();
        }
        return shotScores.hasClear();
    }

    Integer getFrameScore() {
        if (isFrameSet()) {
            return score();
        }
        return null;
    }

    private Integer score() {
        if (isLast) {
            return shotScores.singleScore();
        }
        return shotScores.totalScore();
    }

    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }
}
