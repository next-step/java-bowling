package bowling.domain.state;

import bowling.domain.score.Score;

public class Miss extends Finished {
    public static final String MISS_CAN_NOT_PLAY_ERROR = "Miss 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private PinCount lastPlayPoint;
    private PinCount currentPlayPoint;

    public Miss(PinCount felledPin, PinCount newFelledPin) {
        this.lastPlayPoint = felledPin;
        this.currentPlayPoint = newFelledPin;
    }

    @Override
    public State play(PinCount newFelledPin) {
        throw new IllegalStateException(MISS_CAN_NOT_PLAY_ERROR);
    }

    @Override
    public String getString() {
        return currentPlayPoint.isMinPinCount() ? Gutter.TEXT : String.valueOf(currentPlayPoint.getValue());
    }

    @Override
    public int getFelledPin() {
        return currentPlayPoint.getValue();
    }

    @Override
    public Score createScore() {
        int lastPlayPointValue = lastPlayPoint.getValue();
        int currentPlayPointValue = currentPlayPoint.getValue();

        return new Score(lastPlayPointValue + currentPlayPointValue, 0);
    }


}
