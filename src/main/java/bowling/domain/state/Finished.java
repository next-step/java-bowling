package bowling.domain.state;

public abstract class Finished implements State {

    private static final String CAN_NOT_BOWL_MESSAGE = "[ERROR] 종료된 상태에 볼링공을 굴릴 수 없습니다.";

    @Override
    public State bowl(int pins) {
        throw new IllegalArgumentException(CAN_NOT_BOWL_MESSAGE);
    }

    @Override
    public boolean finish() {
        return true;
    }
}
