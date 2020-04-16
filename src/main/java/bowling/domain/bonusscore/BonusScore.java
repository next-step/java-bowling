package bowling.domain.bonusscore;

import bowling.domain.point.Point;
import bowling.domain.point.Points;

import java.util.ArrayList;
import java.util.List;

/**
 * 스트라이크. 스페어의 보너스 점수를 계산 및 추가하는 객체
 */
public class BonusScore {
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int ZERO_BONUS_COUNT = 0;

    private final Points bonusPoints;
    private final BonusScoreInfo bonusScoreInfo;

    private BonusScore(Points bonusPoints, BonusScoreInfo bonusScoreInfo) {
        this.bonusPoints = bonusPoints;
        this.bonusScoreInfo = bonusScoreInfo;
    }

    public static BonusScore noneBonus(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(frameIndex, ZERO_BONUS_COUNT));
    }

    public static BonusScore strikeBonus(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(frameIndex, STRIKE_BONUS_COUNT));
    }

    public static BonusScore spareBonus(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(frameIndex, SPARE_BONUS_COUNT));
    }

    public void add(int point) {
        bonusPoints.add(new Point(point));
    }

    public boolean isAddable() {
        return bonusPoints.size() < bonusScoreInfo.getBonusCount();
    }

    public boolean isEqualFrameIndex(int frameIndex) {
        return bonusScoreInfo.isEqualFrameIndex(frameIndex);
    }

    public int getTotalBonusPoint() {
        return bonusPoints.getTotalBonusPoint();
    }
}
