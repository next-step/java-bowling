package bowling.domain.state;

public class End extends Finished {

    private static final String TEXT = "";
    public static final String GAME_END_ERROR = "게임이 완료되었습니다. 플레이할 수 없습니다.";
    private final int felledPin;

    public End(int felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(int newFelledPin) {
        throw new IllegalStateException(GAME_END_ERROR);
    }

    @Override
    public String getString() {
        return TEXT;
    }
}
