package bowling.domain.state;

public abstract class Finished implements State {
    @Override
    public boolean isBowl() {
        return false;
    }

    @Override
    public State bowl(final int fallenPinCount) {
        throw new IllegalStateException("프레임이 완료되어 볼을 던질 수 없습니다.");
    }
}
