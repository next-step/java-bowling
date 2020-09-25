package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.score.bonus.BonusScores;
import org.springframework.util.CollectionUtils;

import static bowling.domain.pin.Pin.STRIKE_PIN_COUNT;

public class LastFrame implements Frame {
    private static final String LIMIT_SUM_PIN_MESSAGE = "1구와 2구 점수의 합은 10점을 넘을 수 없습니다.";
    private static final int FIRST_PLAY = 0;

    private final Scores scores;
    private final BonusScores bonusScores;

    public LastFrame(BonusScores bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    private boolean isStrikeOrSpare() {
        return scores.getScores().stream()
                .anyMatch(Score::isClear);
    }

    private boolean isOverMaxCount(int pin) {
        return scores.currentPinCount() + pin > STRIKE_PIN_COUNT;
    }

    private void validateScore(int pin) {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return;
        }
        validatePin(pin);
    }

    private void validatePin(int pin) {
        if (scores.isSecondPlay() && !scores.isStrike(FIRST_PLAY) && isOverMaxCount(pin)) {
            throw new IllegalArgumentException(LIMIT_SUM_PIN_MESSAGE);
        }
    }

    @Override
    public void addScore(int pin) {
        validateScore(pin);
        this.scores.add(Score.lastScore(scores, pin));
        bonusScores.addBonusPin(pin);
    }

    @Override
    public String getScore(int scoreIndex) {
        return scores.pinToScore(scoreIndex);
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores()) || scores.isPlayable()) {
            return true;
        }

        return scores.isBonusPlayable() && isStrikeOrSpare();
    }

    @Override
    public int scoreSize() {
        return scores.size();
    }
}
