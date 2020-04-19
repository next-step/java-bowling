package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScores;
import bowling.domain.scores.LastScores;
import org.springframework.util.CollectionUtils;

/**
 * 마지막 프레임을 나타내기 위한 객체
 * 마지막 프레임에서 스트라이크, 스페어 일 경우 1구를 더 진행한다.
 */
public class LastFrame extends Frame {
    private static final int STRIKE_POINT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int BONUS_PLAY_COUNT = 3;
    private static final int FIRST_PLAY = 0;
    private static final int SECOND_PLAY = 1;
    private static final int DOUBLE_STRIKE_POINT = 20;

    private final BonusScores bonusScores;

    public LastFrame(BonusScores bonusScores) {
        super(new LastScores());
        this.bonusScores = bonusScores;
    }

    @Override
    public void addScore(int point) {
        if (scores.isSecondPlay()) {
            validateSecondPoint(point);
        }

        if (scores.isThirdPlay()) {
            validateThirdPoint(point);
        }

        scores.add(point);
        bonusScores.addBonusPoint(point);
    }

    private void validateSecondPoint(int point) {
        if (!scores.isStrike(FIRST_PLAY) && totalPoint(point) > STRIKE_POINT) {
            throw new IllegalArgumentException("마지막 포인트합은 10점을 넘을수 없습니다.");
        }
    }

    private void validateThirdPoint(int point) {
        if (!scores.isStrike(SECOND_PLAY) && totalPoint(point) > DOUBLE_STRIKE_POINT) {
            throw new IllegalArgumentException("마지막 포인트합은 20점을 넘을수 없습니다.");
        }
    }

    private int totalPoint(int point) {
        return scores.currentPoint() + point;
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return true;
        }
        if (scores.size() < DEFAULT_PLAY_COUNT) {
            return true;
        }

        return scores.size() < BONUS_PLAY_COUNT && scores.hasStrikeOrSpare();
    }

    @Override
    public int getTotalPoint(int frameIndex) {
        return scores.currentPoint();
    }

    @Override
    public boolean isCalculatableFrame(int frameIndex) {
        return !isPlayable();
    }
}
