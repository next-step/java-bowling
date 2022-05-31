package bowling.frame;

import java.util.Objects;

public class ShootScore {

    private static final int GUTTER = 0;
    private static final int STRIKE = 10;

    private final int shootScore;

    private ShootScore(int shootScore) {
        if (isInvalidShootScore(shootScore)) {
            throw new IllegalArgumentException("점수는 0점 이상, 10점 이하에 속해야만 합니다.");
        }
        this.shootScore = shootScore;
    }

    public static ShootScore from(int shootScore) {
        return new ShootScore(shootScore);
    }

    private boolean isInvalidShootScore(int shootScore) {
        return shootScore < GUTTER || shootScore > STRIKE;
    }

    public boolean isStrike() {
        return shootScore == STRIKE;
    }

    public boolean isSpare(ShootScore secondShoot) {
        return this.shootScore + secondShoot.shootScore == STRIKE;
    }

    public boolean isGutter() {
        return this.shootScore == GUTTER;
    }

    public int getShootScore() {
        return shootScore;
    }

    public boolean overMaxShoot(ShootScore shootScore) {
        return this.shootScore + shootScore.shootScore > STRIKE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShootScore that = (ShootScore) o;
        return shootScore == that.shootScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shootScore);
    }
}
