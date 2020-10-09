package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public State pitch(int fallenPins) {
        throw new UnsupportedOperationException("더이상 투구할 수 없습니다.");
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public int getTotalCount() {
        return 10;
    }
}
