package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import java.util.ArrayList;
import java.util.List;

public class NormalPins implements Pins {

    private static final int MIN_PIN_COUNT = 0;
    private static final int PIN_COUNT = 10;
    private static final int MAX_ROUND = 2;

    private static final int STRIKE_PIN_COUNT = 10;
    private static final int FIRST_BOWL_INDEX = 0;
    private static final int FIRST_ROLL = 1;
    private static final int SECOND_ROLL = 2;

    private final List<Integer> downPins = new ArrayList<>();

    @Override
    public void down(int downPin) {
        validate(downPin);
        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        return sumOfDownPin() != PIN_COUNT;
    }

    @Override
    public ScoreType getScoreType() {
        if (isStrike()) {
            return ScoreType.STRIKE;
        }

        if (isSpare()) {
            return ScoreType.SPARE;
        }

        if (!hasTurn()) {
            return ScoreType.MISS;
        }

        return ScoreType.READY;
    }

    @Override
    public List<Integer> getDownPins() {
        return new ArrayList<>(this.downPins);
    }

    @Override
    public int sum() {
        return this.downPins
            .stream()
            .reduce(0, Integer::sum);
    }


    private int sumOfDownPin() {
        return this.downPins
            .stream()
            .reduce(0, Integer::sum);
    }

    private boolean isSpare() {
        return this.downPins.size() == SECOND_ROLL && sumOfDownPin() == STRIKE_PIN_COUNT;
    }

    private boolean isStrike() {
        return this.downPins.size() == FIRST_ROLL
            && this.downPins.get(FIRST_BOWL_INDEX) == STRIKE_PIN_COUNT;
    }

    private void validate(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("이미 다 던졌습니다.");
        }

        if (downPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 0개 보다 더 쓰러질 수 없습니다.");
        }

        if (sumOfDownPin() + downPin > PIN_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 핀 갯수");
        }
    }
}
