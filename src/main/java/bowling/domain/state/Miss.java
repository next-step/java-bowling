package bowling.domain.state;

public class Miss extends Finished {
    public static final String MISS_CAN_NOT_PLAY_ERROR = "Miss 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private int lastPlayPoint;
    private int currentPlayPoint;

    public Miss(int felledPin, int newFelledPin) {
        this.lastPlayPoint = felledPin;
        this.currentPlayPoint = newFelledPin;
    }

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(MISS_CAN_NOT_PLAY_ERROR);
    }

    @Override
    public String getString() {
        return currentPlayPoint == 0 ? Gutter.TEXT : String.valueOf(currentPlayPoint);
    }
}
