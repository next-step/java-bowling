package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean hasBonus;
    private Frame nextFrame;

    protected Frame(List<ShotScore> shotScores, boolean hasBonus) {
        this.shotScores = ShotScores.of(shotScores);
        this.hasBonus = hasBonus;
    }

    static Frame init() {
        return new Frame(new ArrayList<>(), false);
    }

    Frame next(int shot) {
        nextFrame = new Frame(new ArrayList<>(), false);
        nextFrame.shot(shot);
        return nextFrame;
    }

    Frame last(int shot) {
        nextFrame = new Frame(new ArrayList<>(), true);
        nextFrame.shot(shot);
        return nextFrame;
    }

    void shot(int shot) {
        int shotLimit = getShotLimit();
        if (shotScores.isSize(shotLimit)) {
            throw new IllegalStateException(String.format("shot Frame fail. cannot shot over %d times", shotLimit));
        }

        shotScores.add(shot, hasBonus);
    }

    boolean isFrameSet() {
        return shotScores.isSize(getShotLimit()) ||
                (cannotShooting());
    }

    private int getShotLimit() {
        return hasBonus ? SHOT_LIMIT + 1 : SHOT_LIMIT;
    }

    private boolean cannotShooting() {
        if (hasBonus) {
            return shotScores.isSize(SHOT_LIMIT) && !shotScores.isClear();
        }
        return shotScores.isClear();
    }

    public Integer getFrameScore() {
        if (isFrameSet()) {
            if (hasBonus) {
                return getUnBonusScore();
            }
            return getTotalScore();
        }
        return null;
    }

    private Integer getTotalScore() {
        if (shotScores.isClear()) {
            return Optional.ofNullable(nextFrame)
                    .map(next -> next.getBonusScore(frameScoreType()))
                    .map(nextBonus -> nextBonus + getUnBonusScore())
                    .orElse(null);
        }

        return getUnBonusScore();
    }

    private ScoreType frameScoreType() {
        return shotScores.lastScoreType();
    }

    private int getUnBonusScore() {
        return shotScores.totalScore();
    }

    private Integer getBonusScore(ScoreType scoreType) {
        if (scoreType.equals(ScoreType.STRIKE)) {
            if (isFrameSet()) {
                if (frameScoreType().equals(ScoreType.STRIKE)) {
                    return Optional.ofNullable(nextFrame)
                            .map(next -> next.getBonusScore(ScoreType.SPARE))
                            .map(nextBonus -> nextBonus + 10)
                            .orElse(null);
                }
                return shotScores.totalScore(2);
            }

            return null;
        }

        if (scoreType.equals(ScoreType.SPARE)) {
            return shotScores.totalScore(1);
        }

        return 0;
    }

    public ShotScores shotScores() {
        return shotScores;
    }
}
