package bowling.domain.state;

public class Strike implements State {
    public static final String STRIKE_CAN_NOT_PLAY_ERROR = "Strike 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(STRIKE_CAN_NOT_PLAY_ERROR);
    }
}
