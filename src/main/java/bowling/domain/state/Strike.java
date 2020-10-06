package bowling.domain.state;

public class Strike implements State {

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public State pitch(int fallenPins) {
        throw new UnsupportedOperationException("더이상 투구할 수 없습니다.");
    }
}
