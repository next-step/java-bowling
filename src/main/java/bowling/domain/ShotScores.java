package bowling.domain;

import bowling.dto.ShotScoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShotScores {
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
        return !shotScores.isEmpty() && getStream()
                .anyMatch(ShotScore::isClear);
    }

    int totalScore(){
        return sum(getStream());
    }

    int totalScore(int rangeScore){
        return sum(getStream()
                .limit(rangeScore));
    }

    private Stream<ShotScore> getStream() {
        return shotScores
                .stream();
    }

    private int sum(Stream<ShotScore> shotScoreStream){
        return shotScoreStream
                .map(ShotScore::score)
                .mapToInt(Score::score)
                .sum();
    }


    List<ShotScoreDto> getDtoList() {
        return getStream()
                .map(ShotScore::getDto)
                .collect(Collectors.toList());
    }
}
