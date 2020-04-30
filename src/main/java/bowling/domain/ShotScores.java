package bowling.domain;

import bowling.domain.frameScore.DefaultFrameScore;

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

    static ShotScores of() {
        return new ShotScores(new ArrayList<>());
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

    private int singleScore() {
        return shotScores
                .stream()
                .mapToInt(ShotScore::singleScore)
                .sum();
    }

    DefaultFrameScore getCalculateScore() {
        try {
            return DefaultFrameScore.of(Score.of(this.singleScore()), getLast().scoreType());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("ShotScores cannot calculateScore", e);
        }
    }

    List<ShotScore> shotScores() {
        return new ArrayList<>(shotScores);
    }
}
