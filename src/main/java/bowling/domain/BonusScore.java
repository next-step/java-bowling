package bowling.domain;

public class BonusScore {

    private static final int FINAL_ROUND = 10;
    private static final int EMPTY_BONUS_POINT = 0;

    private final Point bonusPoint;

    private BonusScore(Point bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    private BonusScore() {
        this.bonusPoint = Point.of(EMPTY_BONUS_POINT);
    }

    public static BonusScore empty() {
        return new BonusScore();
    }

    public static BonusScore of(Round round, Score score, Point bonusPoint) {
        BowlingRole type = score.type();
        if (type != BowlingRole.SPARE && type != BowlingRole.STRIKE) {
            throw new IllegalArgumentException("스트라이크나 스페어를 해야 보너스 투구를 할 수 있습니다.");
        }

        if (!round.equals(Round.of(FINAL_ROUND))) {
            throw new IllegalArgumentException("마지막 라운드에서만 보너스 투구를 할 수 있습니다.");
        }
        return new BonusScore(bonusPoint);
    }

    public int point() {
        return bonusPoint.toInt();
    }
}
