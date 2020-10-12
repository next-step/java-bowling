package bowling.domain.state;

public abstract class Finished implements State {
    @Override
    public State pitch(int fallenPins) {
        throw new UnsupportedOperationException("더이상 투구할 수 없습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
