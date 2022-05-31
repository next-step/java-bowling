package bowling.domain.state;

public class Spare implements State {

    private final int firstHitCount;

    public Spare(int firstHitCount) {
        this.firstHitCount = firstHitCount;
    }

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException("스페어 이 후에는 볼을 더 던질 수 없음");
    }

    @Override
    public boolean isDone() {
        return true;
    }

}
