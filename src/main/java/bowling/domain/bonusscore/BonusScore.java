package bowling.domain.bonusscore;

import java.util.ArrayList;
import java.util.List;

/**
 * 스트라이크. 스페어의 보너스 점수를 계산 및 추가하는 객체
 */
public class BonusScore {
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int ZERO_BONUS_COUNT = 0;

    private final List<Integer> bonusScores;
    private final BonusScoreInfo bonusScoreInfo;

    private BonusScore(List<Integer> bonusScores, BonusScoreInfo bonusScoreInfo) {
        this.bonusScores = bonusScores;
        this.bonusScoreInfo = bonusScoreInfo;
    }

    public static BonusScore noneBonus(int frameIndex) {
        return new BonusScore(new ArrayList<>(), new BonusScoreInfo(frameIndex, ZERO_BONUS_COUNT));
    }

    public static BonusScore strikeBonus(int frameIndex) {
        return new BonusScore(new ArrayList<>(), new BonusScoreInfo(frameIndex, STRIKE_BONUS_COUNT));
    }

    public static BonusScore spareBonus(int frameIndex) {
        return new BonusScore(new ArrayList<>(), new BonusScoreInfo(frameIndex, SPARE_BONUS_COUNT));
    }

    public void add(int score) {
        bonusScores.add(score);
    }

    public boolean isAddable() {
        return bonusScores.size() < bonusScoreInfo.getBonusCount();
    }

    public boolean isEqualFrameIndex(int frameIndex) {
        return bonusScoreInfo.isEqualFrameIndex(frameIndex);
    }

    public int getTotalBonusPoint() {
        return bonusScores.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
