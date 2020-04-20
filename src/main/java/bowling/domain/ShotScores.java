package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShotScores {
    private final List<ShotScore> shotScores;

    private ShotScores(List<ShotScore> shotScores) {
        this.shotScores = new ArrayList<>(shotScores);
    }

    static ShotScores of(List<ShotScore> shotScores){
        return new ShotScores(shotScores);
    }

    private ShotScore getLast() {
        return shotScores.get(shotScores.size() - 1);
    }

    void add(int shotScore, boolean canShootingBonus) {
        if (shotScores.isEmpty() || checkCanShootingBonus(canShootingBonus)) {
            shotScores.add(ShotScore.of(shotScore));
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

    boolean isClear() {
        return !shotScores.isEmpty() && stream()
                .anyMatch(ShotScore::isClear);
    }

    int totalScore(){
        return sum(stream());
    }

    int totalScore(int rangeScore){
        return sum(stream()
                .limit(rangeScore));
    }

    ScoreType lastScoreType(){
        return this.getLast().scoreType();
    }

    public Stream<ShotScore> stream() {
        return shotScores
                .stream();
    }

    private int sum(Stream<ShotScore> shotScoreStream){
        return shotScoreStream
                .map(ShotScore::score)
                .mapToInt(Score::score)
                .sum();
    }
}
