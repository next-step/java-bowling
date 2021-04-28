package step4.domain.state;

public abstract class Finished implements State{
    private static final String CANNOT_THROW_BOWL_ANYMORE = "완료된 상태이기 때문에 더 이상 공을 던질 수 없습니다.";

    @Override
    public State bowl(int pinCount) {
        throw new IllegalStateException(CANNOT_THROW_BOWL_ANYMORE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
