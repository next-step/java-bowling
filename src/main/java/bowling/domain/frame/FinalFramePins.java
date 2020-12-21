package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import java.util.ArrayList;
import java.util.List;

public class FinalFramePins implements Pins {

    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;
    private static final int BONUS_SPARE_PIN_COUNT = 20;
    private static final int BONUS_FRAME_SIZE = 3;
    private final List<Integer> bonusDownPins;
    private final Pins pins;

    private FinalFramePins(Pins pins) {
        this.pins = pins;
        this.bonusDownPins = new ArrayList<>();
    }

    public static Pins create() {
        return new FinalFramePins(new NormalPins());
    }

    @Override
    public void down(int downPin) {
        validate(downPin);

        if (this.pins.hasTurn()) {
            this.pins.down(downPin);
            return;
        }

        this.bonusDownPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.pins.hasTurn()) {
            return true;
        }

        return this.bonusDownPins.size() < getBonusCount();
    }

    @Override
    public ScoreType getScoreType() {
        if (this.pins.getScoreType() == ScoreType.STRIKE && isBonusFrameSpare()) {
            return ScoreType.STRIKE_AND_SPARE;
        }
        return this.pins.getScoreType();
    }

    @Override
    public List<Integer> getDownPins() {
        List<Integer> bonusDownPins = new ArrayList<>(this.pins.getDownPins());
        bonusDownPins.addAll(this.bonusDownPins);
        return bonusDownPins;
    }

    @Override
    public Score frameScore(FrameNumber frameNumber, Scores scores) {
        if (!hasTurn()) {
            return Score.create(sum(), ScoreType.NORMAL);
        }
        return pins.frameScore(frameNumber, scores);
    }

    @Override
    public int sum() {
        return this.pins.sum() + this.bonusDownPins.stream().reduce(0, Integer::sum);
    }

    private void validate(int downPin) {
        if (downPin < MIN_PIN || downPin > MAX_PIN) {
            throw new IllegalArgumentException("0보다 작거나 10보다 클 수 없습니다.");
        }

        if (!hasTurn()) {
            throw new IllegalStateException("이미 다 던졌습니다.");
        }
    }

    private int getBonusCount() {
        return getScoreType().getBonusBowlCount();
    }

    private boolean isBonusFrameSpare() {
        if (this.pins.getDownPins().size() + bonusDownPins.size() == BONUS_FRAME_SIZE) {
            return sum() == BONUS_SPARE_PIN_COUNT;
        }
        return false;
    }
}
