package bowling.domain.state;

public class Spare implements State {
    public static final String SPARE_CAN_NOT_PLAY_ERROR = "Spare 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private static final String TEXT = "/";

    private int lastPlayPoint;
    private int currentPlayPoint;

    public Spare(int felledPin, int newFelledPin) {
        this.lastPlayPoint = felledPin;
        this.currentPlayPoint = newFelledPin;
    }

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(SPARE_CAN_NOT_PLAY_ERROR);
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
