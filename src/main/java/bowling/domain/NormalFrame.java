package bowling.domain;

import java.util.ArrayList;

public class NormalFrame extends DefaultFrame {

    private static final int FIRST_SCORE = 0;
    private static final int NORMAL_SCORE_TRY_COUNT = 2;

    public NormalFrame() {
        super(new ArrayList<>());
    }

    @Override
    public NormalFrame addScore(Pins pins) {
        if (isStrike()) {
            throw new IllegalArgumentException("스트라이크");
        }

        if (!score.isEmpty()) {
            if (score.get(FIRST_SCORE).sumCount(pins) > 10) {
                throw new IllegalArgumentException("10보다 클 수 없음.");
            }
            if (score.size() >= NORMAL_SCORE_TRY_COUNT) {
                throw new IllegalArgumentException("2번 이상 할 수 없음.");
            }
        }
        score.add(pins);
        return this;
    }

}
