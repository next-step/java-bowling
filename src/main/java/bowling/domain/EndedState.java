package bowling.domain;

public abstract class EndedState implements State {
    @Override
    public State bowl(Pitching pitching) {
        throw new UnsupportedOperationException(String.format("(%s) 상태는 프레임이 종료된 상태로 투구를 할 수 없습니다.", getClass().getSimpleName()));
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
