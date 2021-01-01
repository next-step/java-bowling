package step2.domain.state;

public abstract class Finished implements State {

    @Override
    public State bowl(int fallingPins) {
        throw new IllegalArgumentException("더 이상 투구를 할 수 없습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

}
