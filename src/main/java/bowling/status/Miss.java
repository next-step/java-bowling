package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

import java.util.Objects;

import static bowling.status.StatusBoardFactory.drawGutterOrScore;

public class Miss implements Status {

    private static final String DIVIDER = "|";

    private final ShootScore firstShoot;
    private final ShootScore secondShoot;

    private Miss(ShootScore firstShoot, ShootScore secondShoot) {
        if (firstShoot.isStrike() || secondShoot.isStrike()) {
            throw new IllegalArgumentException("Miss 는 두 개의 투구 모두 스트라이크가 될 수 없습니다.");
        }

        if (firstShoot.isSpare(secondShoot)) {
            throw new IllegalArgumentException("Miss 는 첫 번째, 두 번째 슛의 합이 10 미만이어야 합니다. (스페어면 안됩니다.)");
        }
        this.firstShoot = firstShoot;
        this.secondShoot = secondShoot;
    }

    public static Miss of(ShootScore firstShoot, ShootScore secondShoot) {
        return new Miss(firstShoot, secondShoot);
    }

    private int totalScore() {
        return firstShoot.getShootScore() + secondShoot.getShootScore();
    }

    @Override
    public Status shoot(ShootScore shootScore) {
        throw new UnsupportedOperationException("2번의 투구를 모두 하셨습니다");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String board() {
        return drawGutterOrScore(firstShoot) + DIVIDER + drawGutterOrScore(secondShoot);
    }

    @Override
    public Score createScore() {
        return Score.toMiss(totalScore());
    }

    @Override
    public int ownScore() {
        return totalScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstShoot, miss.firstShoot) && Objects.equals(secondShoot, miss.secondShoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstShoot, secondShoot);
    }
}
