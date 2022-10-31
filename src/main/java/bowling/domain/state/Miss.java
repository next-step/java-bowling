package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

import java.util.Objects;

public class Miss extends Finished {
    private final FallenPin firstPin;
    private final FallenPin secondPin;

    public Miss(FallenPin firstPin, FallenPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String description() {
        String result = firstPin.getCount() + "|" + secondPin.getCount();
        return result.replace("0", Symbol.GUTTER);
    }

    @Override
    public int tries() {
        return 2;
    }

    @Override
    public Score getScore() {
        return new Score(firstPin.add(secondPin), 0);
    }

    @Override
    public Score addScore(Score previousScore) {
        Score score = previousScore.bowl(firstPin.getCount());
        if (score.canCalculate()) {
            return score;
        }
        return score.bowl(secondPin.getCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miss)) return false;

        Miss miss = (Miss) o;

        if (!Objects.equals(firstPin, miss.firstPin)) return false;
        return Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        int result = firstPin != null ? firstPin.hashCode() : 0;
        result = 31 * result + (secondPin != null ? secondPin.hashCode() : 0);
        return result;
    }
}
