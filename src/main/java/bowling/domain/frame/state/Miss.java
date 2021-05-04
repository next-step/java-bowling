package bowling.domain.frame.state;

import bowling.domain.Score;

public class Miss implements State {
    private final int remainPins;
    private final int fellPins;

    public Miss(int remainPins, int falledPins) {
        this.remainPins = remainPins;
        this.fellPins = falledPins;
    }

    @Override
    public State nextState() {
        throw new IllegalStateException("최종 상태 입니다 : " + this.getClass());
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void bowl(int fellPins) {
        throw new IllegalStateException("플레이가 끝난 최종 상태에선 투구를 할 수 없습니다 : " + this.getClass());
    }

    @Override
    public Score calculateScore() {
        return new Score(10 - remainPins, countToCalculate());
    }

    @Override
    public boolean isCalculable() {
        return false;
    }

    @Override
    public int getStateValue() {
        return fellPins;
    }

    @Override
    public int countToCalculate() {
        return 0;
    }
}
