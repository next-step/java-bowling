package bowling.domain.state;

public class Miss implements State {
    public static final String MISS_CAN_NOT_PLAY_ERROR = "Miss 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private int lastPlayPoint;
    private int currntPlayPoint;

    public Miss(int felledPin, int newFelledPin) {
        this.lastPlayPoint = felledPin;
        this.currntPlayPoint = newFelledPin;
    }

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(MISS_CAN_NOT_PLAY_ERROR);
    }

    @Override
    public boolean isEndedState() {
        return true;
    }

    @Override
    public String getString() {
        return currntPlayPoint == 0 ? Gutter.TEXT : String.valueOf(currntPlayPoint);
    }
}
