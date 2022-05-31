package bowling.domain.state;

public class Strike implements State {

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException("스트라이크 이 후에는 볼을 더 던질 수 없음");
    }

    @Override
    public boolean isDone() {
        return true;
    }

}
