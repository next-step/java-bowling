package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.ShotScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean hasBonus;
    private Frame nextFrame;

    protected Frame(List<ShotScore> shotScores, boolean hasBonus) {
        this.shotScores = new ShotScores(shotScores);
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
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", shotLimit));
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

    FrameDto getDto() {
        return new FrameDto(shotScores.getDtoList(), getFrameScore());
    }

    private Integer getFrameScore() {
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
                    .map(next -> next.getBonusScore(getScoreType()))
                    .map(nextBonus -> nextBonus + getUnBonusScore())
                    .orElse(null);
        }

        return getUnBonusScore();
    }

    private ScoreType getScoreType() {
        return shotScores.getDtoList().get(shotScores.getDtoList().size() - 1).getScoreType();
    }

    private int getUnBonusScore() {
        return shotScores.getDtoList().stream()
                .map(ShotScoreDto::getScore)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer getBonusScore(ScoreType scoreType) {
        if (scoreType.equals(ScoreType.STRIKE)) {
            if (isFrameSet()) {
                if (getScoreType().equals(ScoreType.STRIKE)) {
                    return Optional.ofNullable(nextFrame)
                            .map(next -> next.getBonusScore(ScoreType.SPARE))
                            .map(nextBonus -> nextBonus + 10)
                            .orElse(null);
                }
                return shotScores.getDtoList().stream()
                        .limit(2)
                        .map(ShotScoreDto::getScore)
                        .mapToInt(Integer::intValue)
                        .sum();
            }

            return null;
        }

        if (scoreType.equals(ScoreType.SPARE)) {
            return shotScores.getDtoList().get(0).getScore();
        }

        return 0;
    }
}
