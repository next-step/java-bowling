package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

import java.util.Objects;

import static bowling.status.StatusBoardFactory.drawGutterOrScore;

public class FirstShoot implements Status {

    private final ShootScore firstShoot;

    private FirstShoot(ShootScore firstShoot) {
        if (firstShoot.isStrike()) {
            throw new IllegalArgumentException("첫 번째 투구가 스트라이크라면 FirstShoot 상태를 생성할 수 없습니다.");
        }
        this.firstShoot = firstShoot;
    }

    public static FirstShoot from(ShootScore firstShoot) {
        return new FirstShoot(firstShoot);
    }

    @Override
    public Status shoot(ShootScore secondShoot) {
        if (firstShoot.isSpare(secondShoot)) {
            return Spare.from(firstShoot);
        }

        return Miss.of(firstShoot, secondShoot);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String board() {
        return drawGutterOrScore(firstShoot);
    }

    @Override
    public Score createScore() {
        throw new UnsupportedOperationException("첫 번째 슛 상태에서는 점수를 산정할 수 없습니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstShoot that = (FirstShoot) o;
        return Objects.equals(firstShoot, that.firstShoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstShoot);
    }
}
