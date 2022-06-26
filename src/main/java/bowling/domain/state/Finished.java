package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.IllegalBowlException;

abstract class Finished extends AbstractState {

    Finished(int fallenPins, String symbol) {
        super(fallenPins, symbol);
    }

    @Override
    public AbstractState bowl(int fallenPins) {
        throw new IllegalBowlException("실행할 수 없는 메서드 입니다.");
    }

    @Override
    public Score calculateAdditionalScore(Score score, int fallenPins) {
        return score.bowl(fallenPins);
    }
}
