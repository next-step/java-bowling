package domain.state;

import domain.Pins;

import static io.OutputResult.SYMBOL_DELIMITER;

public class Open implements State {
    private final Pins first;
    private final Pins second;

    public Open(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (isClear(first, second)) {
            throw new IllegalArgumentException("상태와 맞지 않는 정보 입니다.");
        }
    }

    private boolean isClear(Pins first, Pins second) {
        return Pins.ALL.equals(first.add(second));
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public String toSymbol() {
        return first + SYMBOL_DELIMITER + second;
    }


}
