package bowling.status;

import bowling.frame.ShootScore;
import bowling.score.Score;

import java.util.Objects;

import static bowling.status.StatusBoardFactory.drawGutterOrScore;

public class Spare implements Status {

    private static final String DIVIDER = "|";
    private static final String SPARE_SIGNATURE = "/";

    private static final int MAX_SCORE = 10;

    private final ShootScore firstShoot;

    private Spare(ShootScore firstShoot) {
        if (firstShoot.isStrike()) {
            throw new IllegalArgumentException("Spare 상태는 첫 투구가 스트라이크 일 수 없습니다.");
        }
        this.firstShoot = firstShoot;
    }

    public static Spare from(ShootScore firstShoot) {
        return new Spare(firstShoot);
    }

    @Override
    public Status shoot(ShootScore shootScore) {
        throw new UnsupportedOperationException("마지막 프레임을 제외한 일반 프레임에서 스페어는 더 투구하지 않습니다");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String board() {
        return drawGutterOrScore(firstShoot) + DIVIDER + SPARE_SIGNATURE;
    }

    @Override
    public Score createScore() {
        return Score.toSpare();
    }

    @Override
    public int ownScore() {
        return MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstShoot, spare.firstShoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstShoot);
    }
}
