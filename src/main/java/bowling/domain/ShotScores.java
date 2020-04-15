package bowling.domain;

import bowling.dto.ShotScoreDto;

import java.util.List;
import java.util.stream.Collectors;

class ShotScores {
    private final List<ShotScore> shotScores;

    ShotScores(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    ShotScore getLast() {
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

    public boolean isSize(int size) {
        return shotScores.size() == size;
    }

    boolean isClear() {
        return !shotScores.isEmpty() && shotScores.stream()
                .anyMatch(ShotScore::isClear);
    }

    List<ShotScoreDto> getDtoList() {
        return shotScores.stream()
                .map(ShotScore::getDto)
                .collect(Collectors.toList());
    }
}
