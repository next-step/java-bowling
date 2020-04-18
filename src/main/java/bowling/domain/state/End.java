package bowling.domain.state;

import bowling.domain.score.Score;

public class End extends Finished {

    public static final String GAME_END_ERROR = "게임이 완료되었습니다. 플레이할 수 없습니다.";
    private static final String TEXT = "";
    private final PinCount felledPin;

    public End(PinCount felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(PinCount newFelledPin) {
        throw new IllegalStateException(GAME_END_ERROR);
    }

    @Override
    public String getString() {
        return String.valueOf(felledPin.getValue());
    }

    @Override
    public int getFelledPin() {
        return felledPin.getValue();
    }

    @Override
    public Score createScore() {
        return new Score(felledPin.getValue(), 0);
    }
}
