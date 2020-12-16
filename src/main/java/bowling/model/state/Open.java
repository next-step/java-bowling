package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.finishedState.Miss;
import bowling.model.state.finishedState.Spare;

public class Open extends State {
    private static final String OPEN_STRIKE_ERROR = "오픈 상태는 스트라이크를 가질 수 없습니다.";

    private Open(Pins firstScore) {
        pins = firstScore;
    }

    public static Open from(Pins pins) {
        if (pins.isMaxScore()) {
            throw new IllegalArgumentException(OPEN_STRIKE_ERROR);
        }
        return new Open(pins);
    }

    @Override
    public State bowling(int fallenPin) {
        Pins secondFallenPins = Pins.from(fallenPin);
        Pins totalScore = pins.add(secondFallenPins);

        if (totalScore.isMaxScore()) {
            return Spare.of(pins, secondFallenPins);
        }

        return Miss.of(pins, secondFallenPins);
    }

    @Override
    public Score score() {
        return Score.of(pins.getScore(),1);
    }

    @Override
    public String toString() {
        return pins.toString();
    }
}
