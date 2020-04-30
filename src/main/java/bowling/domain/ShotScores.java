package bowling.domain;

import bowling.domain.scoreType.ScoreType;

import java.util.ArrayList;
import java.util.List;

public class ShotScores {
    private final List<ShotScore> shotScores;

    private ShotScores(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static ShotScores of(List<ShotScore> shotScores) {
        return new ShotScores(shotScores);
    }

    public static ShotScores of() {
        return new ShotScores(new ArrayList<>());
    }

    public void add(ShotScore shotScore) {
        shotScores.add(shotScore);
    }

    public ShotScore getNext(int shot) {
        if (shotScores.isEmpty()) {
            return ShotScore.init(shot);
        }
        return getLast().next(shot);
    }

    private ShotScore getLast() {
        return shotScores.get(shotScores.size() - 1);
    }

    public boolean hasSize(int size) {
        return shotScores.size() == size;
    }

    public boolean hasClear() {
        return shotScores.stream()
                .anyMatch(ShotScore::isClear);
    }

    public int totalScore() {
        return shotScores
                .stream()
                .mapToInt(ShotScore::singleScore)
                .sum();
    }

    public ScoreType getLastType() {
        return getLast().scoreType();
    }

    public List<ShotScore> shotScores() {
        return new ArrayList<>(shotScores);
    }

    @Override
    public String toString() {
        return "ShotScores{" +
                "shotScores=" + shotScores +
                '}';
    }
}
