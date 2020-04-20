package bowling.domain;

import java.util.ArrayList;
import java.util.List;

class ShotScores {
    private final List<ShotScore> shotScores;

    private ShotScores(List<ShotScore> shotScores) {
        this.shotScores = new ArrayList<>(shotScores);
    }

    static ShotScores of(List<ShotScore> shotScores) {
        return new ShotScores(shotScores);
    }

    private ShotScore getLast() {
        return shotScores.get(shotScores.size() - 1);
    }

    void add(int shotScore, boolean canShootingBonus) {
        if (shotScores.isEmpty() || checkCanShootingBonus(canShootingBonus)) {
            shotScores.add(ShotScore.init(shotScore));
            return;
        }

        shotScores.add(this.getLast().next(shotScore));
    }

    private boolean checkCanShootingBonus(boolean hasBonusShot) {
        return this.getLast().isClear() && hasBonusShot;
    }

    boolean isSize(int size) {
        return shotScores.size() == size;
    }

    boolean hasClear() {
        return !shotScores.isEmpty() &&
                shotScores.stream()
                .anyMatch(ShotScore::isClear);
    }

    ScoreType lastScoreType() {
        return this.getLast().scoreType();
    }

    int totalScore() {
        return totalScore(shotScores.size());
    }

    int totalScore(int rangeScore) {
        return shotScores
                .stream()
                .limit(rangeScore)
                .mapToInt(ShotScore::singleScore)
                .sum();
    }

    List<ShotScore> shotScores() {
        return shotScores;
    }
}
