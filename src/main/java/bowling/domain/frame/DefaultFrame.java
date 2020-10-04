package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.score.bonus.BonusScore;
import bowling.domain.score.bonus.BonusScores;
import org.springframework.util.CollectionUtils;

import static bowling.domain.pin.Pin.STRIKE_PIN_COUNT;

public class DefaultFrame implements Frame {
    private static final String LIMIT_FRAME_PIN_MESSAGE = "한 프레임 점수는 10점을 넘을수 없습니다.";
    private static final int FIRST_PLAY = 0;
    private static final int SECOND_PLAY = 1;

    private final Scores scores;
    private final BonusScores bonusScores;

    private DefaultFrame(BonusScores bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    public static DefaultFrame firstFrame() {
        return new DefaultFrame(new BonusScores());
    }

    public DefaultFrame nextFrame(int frameIndex) {
        bonusScores.add(createBonusScore(frameIndex));
        return new DefaultFrame(bonusScores.findAddableBonusScores());
    }

    public LastFrame lastFrame(int frameIndex) {
        bonusScores.add(createBonusScore(frameIndex));
        return new LastFrame(bonusScores.findAddableBonusScores());
    }

    private BonusScore createBonusScore(int frameIndex) {
        if (scores.isStrike(FIRST_PLAY)) {
            return BonusScore.strikeBonus(frameIndex);
        }
        if (scores.isSpare(SECOND_PLAY)) {
            return BonusScore.spareBonus(frameIndex);
        }
        return BonusScore.missBonus(frameIndex);
    }

    private void validateMaxPinSize(int pin) {
        if (scores.currentPinCount() + pin > STRIKE_PIN_COUNT) {
            throw new IllegalArgumentException(LIMIT_FRAME_PIN_MESSAGE);
        }
    }

    public Scores getScores() {
        return scores;
    }

    @Override
    public void addScore(int pin) {
        validateMaxPinSize(pin);
        scores.add(Score.defaultScore(scores, pin));
        bonusScores.addBonusPin(pin);
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return true;
        }
        if (scores.isStrike(FIRST_PLAY)) {
            return false;
        }
        return scores.isPlayable();
    }

    @Override
    public String getScore(int scoreIndex) {
        return scores.pinToScore(scoreIndex);
    }

    @Override
    public int scoreSize() {
        return scores.size();
    }
}
