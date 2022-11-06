package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

public class FirstBowling extends Running {
    private static final int MAX_PINS_SUM = 10;

    private final FallenPin firstPin;

    public FirstBowling(FallenPin fallenPin) {
        this.firstPin = fallenPin;
    }

    @Override
    public FrameState bowl(FallenPin secondPin) {
        int sum = firstPin.add(secondPin);
        if (sum > MAX_PINS_SUM) {
            throw new IllegalArgumentException(String.format("쓰러진 핀의 합은 %s 이하여야 합니다.", MAX_PINS_SUM));
        }

        if (sum == MAX_PINS_SUM) {
            return new Spare(firstPin, secondPin);
        }

        return new Miss(firstPin, secondPin);
    }

    @Override
    public List<FallenPin> getFallenPins() {
        return List.of(firstPin);
    }

    @Override
    public int tries() {
        return 1;
    }

    @Override
    public Score getScore() {
        return new Score(firstPin.getCount(), 0);
    }

    @Override
    public Score addScore(Score previousScore) {
        return previousScore.bowl(firstPin.getCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FirstBowling)) return false;

        FirstBowling that = (FirstBowling) o;

        return Objects.equals(firstPin, that.firstPin);
    }

    @Override
    public int hashCode() {
        return firstPin != null ? firstPin.hashCode() : 0;
    }
}
