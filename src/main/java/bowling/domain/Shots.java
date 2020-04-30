package bowling.domain;

import bowling.domain.shot.type.ScoreType;
import bowling.domain.shot.Shot;

import java.util.ArrayList;
import java.util.List;

public class Shots {
    private final List<Shot> shots;

    private Shots(List<Shot> shots) {
        this.shots = shots;
    }

    static Shots of(List<Shot> shots) {
        return new Shots(shots);
    }

    public static Shots of() {
        return new Shots(new ArrayList<>());
    }

    public void add(Shot shot) {
        shots.add(shot);
    }

    public Shot getNext(int shot) {
        if (shots.isEmpty()) {
            return Shot.init(shot);
        }
        return getLast().next(shot);
    }

    private Shot getLast() {
        return shots.get(shots.size() - 1);
    }

    public boolean hasSize(int size) {
        return shots.size() == size;
    }

    public boolean hasClear() {
        return shots.stream()
                .anyMatch(Shot::isClear);
    }

    public int totalScore() {
        return shots
                .stream()
                .mapToInt(Shot::singleScore)
                .sum();
    }

    public ScoreType getLastType() {
        return getLast().scoreType();
    }

    public List<Shot> shotScores() {
        return new ArrayList<>(shots);
    }

    @Override
    public String toString() {
        return "ShotScores{" +
                "shotScores=" + shots +
                '}';
    }
}
