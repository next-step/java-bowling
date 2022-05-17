package bowling.status;

import bowling.frame.ShootScore;

import java.util.Objects;

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
