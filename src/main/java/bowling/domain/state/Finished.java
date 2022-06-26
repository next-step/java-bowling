package bowling.domain.state;

import bowling.exception.IllegalBowlException;

abstract class Finished extends AbstractState {

    Finished(int fallenPins, String symbol) {
        super(fallenPins, symbol);
    }

    @Override
    public AbstractState bowl(int fallenPins) {
        throw new IllegalBowlException("실행할 수 없는 메서드 입니다.");
    }
}
