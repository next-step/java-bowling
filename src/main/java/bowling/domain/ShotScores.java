package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class ShotScores {
    private final List<ShotScore> shotScores;

    private ShotScores(List<ShotScore> shotScores) {
        this.shotScores = new ArrayList<>(shotScores);
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
        return shotScores.get(shotScores.size() - 1).next(shot);
    }

    boolean isSize(int size) {
        return shotScores.size() == size;
    }

    boolean hasClear() {
        return !shotScores.isEmpty() &&
                shotScores.stream()
                        .anyMatch(ShotScore::isClear);
    }

    Integer totalScore() {
        if (isTotalScoreUnCalculated()) {
            return null;
        }
        return totalScoreStream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    Integer singleScore() {
        return shotScores
                .stream()
                .mapToInt(ShotScore::singleScore)
                .sum();
    }

    private boolean isTotalScoreUnCalculated() {
        return totalScoreStream()
                .anyMatch(Objects::isNull);
    }

    private Stream<Integer> totalScoreStream() {
        return shotScores
                .stream()
                .map(ShotScore::totalScore);
    }

    List<ShotScore> shotScores() {
        return shotScores;
    }
}
