package bowling.score;

import bowling.frame.FrameScoreType;
import bowling.score.rollling.Rolling;

import java.util.ArrayList;
import java.util.List;

import static bowling.score.rollling.Pin.MAX_PIN_NUMBER;

public class BonusScore implements Score {
    private static final String BONUS_WITH_MISS_EXCEPTION = "보너스 게임은 스트라이크/스페어에서만 발생합니다";
    private static final String BONUS_COUNT_EXCEPTION = "보너스 게임 포함 총 3번의 게임만 실행 가능합니다.";
    private static final String ADDITIONAL_BONUS_EXCEPTION = "세번 째 보너스 게임은 더블 스트라이크 시에만 가능합니다.";
    private List<Rolling> bonusRollings = new ArrayList<>();

    public void addScore(FrameScoreType frameScoreType, int score) {
        validBonusGame(frameScoreType);
        validBonusGameCount(frameScoreType);
        this.bonusRollings.add(Rolling.of(score));
    }

    private void validBonusGame(FrameScoreType frameScoreType) {
        if (frameScoreType == FrameScoreType.MISS || frameScoreType == FrameScoreType.PENDING) {
            throw new IllegalArgumentException(BONUS_WITH_MISS_EXCEPTION);
        }
    }

    private void validBonusGameCount(FrameScoreType frameScoreType) {
        if (bonusRollings.size() == 2) {
            throw new IllegalArgumentException(BONUS_COUNT_EXCEPTION);
        }

        if (bonusRollings.size() == 1 &&
                (frameScoreType == FrameScoreType.SPARE || bonusRollings.get(0) != Rolling.of(MAX_PIN_NUMBER))) {
            throw new IllegalArgumentException(ADDITIONAL_BONUS_EXCEPTION);
        }
    }

    public int getNeedScoreCount() {
        if (this.bonusRollings.size() == 2) {
            return 0;
        }
        if (this.bonusRollings.get(0).getScore() == 10) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Rolling> getRollings() {
        return this.bonusRollings;
    }

    @Override
    public int sumScore() {
        return bonusRollings.stream()
                .map(Rolling::getScore)
                .reduce(Integer::sum).orElse(0);
    }
}
