package bowling.domain;

import java.util.ArrayList;
import java.util.List;

class ShotScores {
    private final List<ShotScore> shotScores;

    private ShotScores(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static ShotScores of(List<ShotScore> shotScores) {
        return new ShotScores(shotScores);
    }

    void add(ShotScore shotScore) {
        shotScores.add(shotScore);
    }

    ShotScore getNext(int shot) {
        if (shotScores.isEmpty()) {
            return ShotScore.init(shot);
        }
        return getLast().next(shot);
    }

    private ShotScore getLast() {
        return shotScores.get(shotScores.size() - 1);
    }

    boolean hasSize(int size) {
        return shotScores.size() == size;
    }

    boolean hasClear() {
        return shotScores.stream()
                .anyMatch(ShotScore::isClear);
    }

    int totalScore() {
        return shotScores
                .stream()
                .mapToInt(ShotScore::totalScore)
                .sum();
    }

    int singleScore() {
        return shotScores
                .stream()
                .mapToInt(ShotScore::singleScore)
                .sum();
    }

    boolean isScoreCalculated() {
        return shotScores
                .stream()
                .allMatch(ShotScore::isScoreCalculated);
    }

    List<ShotScore> shotScores() {
        return new ArrayList<>(shotScores);
    }
}
