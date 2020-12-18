package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.finished.Miss;
import bowling.model.state.finished.Spare;

public class Open implements State {
    private static final String OPEN_STRIKE_ERROR = "오픈 상태는 스트라이크를 가질 수 없습니다.";
    private Pins pins;

    private Open(Pins firstScore) {
        this.pins = firstScore;
    }

    public static Open from(Pins pins) {
        if (pins.isMaxScore()) {
            throw new IllegalArgumentException(OPEN_STRIKE_ERROR);
        }
        return new Open(pins);
    }

    @Override
    public Score calculateScore(Score score) {
        Score totalScore = score.add(pins.getScore());
        return totalScore.canCalculate() ? totalScore : score;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State bowling(Pins fallenPin) {
        Pins totalScore = pins.add(fallenPin);

        if (totalScore.isMaxScore()) {
            return Spare.of(pins, fallenPin);
        }

        return Miss.of(pins, fallenPin);
    }

    @Override
    public Score score() {
        return Score.of(pins.getScore(), 1);
    }

    @Override
    public String toString() {
        return pins.toString();
    }
}
