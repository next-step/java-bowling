package bowling.domain;

import java.util.ArrayList;

public class FinalFrame extends DefaultFrame {

    public FinalFrame() {
        super(new ArrayList<>());
    }

    @Override
    public FinalFrame addScore(Pins pins) {
        if (score.size() > 2) {
            throw new IllegalArgumentException("진행할 수 없는 프레임입니다.");
        }

        if (score.size() == 2) {
            if (!isLastScore()) {
                throw new IllegalArgumentException("마지막꺼불가");
            }
            score.add(pins);
            return this;
        }

        score.add(pins);
        return this;
    }

    private boolean isLastScore() {
        return isStrike() || isSpare();
    }

}
