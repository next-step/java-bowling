package bowling.domain.state;

public class Miss implements State {
    public static final String MISS_CAN_NOT_PLAY_ERROR = "Miss 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private int firstPlayPoint;
    private int secondPlayPoint;

    public Miss(int felledPin, int newFelledPin) {
        this.firstPlayPoint = felledPin;
        this.secondPlayPoint = newFelledPin;
    }

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(MISS_CAN_NOT_PLAY_ERROR);
    }
}
