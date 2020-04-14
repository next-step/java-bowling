package bowling.domain.state;

public class Strike implements State {
    public static final String STRIKE_CAN_NOT_PLAY_ERROR = "Strike 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private static final String TEXT = "X";

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(STRIKE_CAN_NOT_PLAY_ERROR);
    }

    @Override
    public boolean isEndedState() {
        return true;
    }

    @Override
    public String getString() {
        return TEXT;
    }

    @Override
    public boolean canAdditionalFrame() {
        return true;
    }
}
