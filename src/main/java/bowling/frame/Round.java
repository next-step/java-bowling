package bowling.frame;

import java.util.List;

public class Round {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 10;

    private int round;

    private Round(int round) {
        if (isInvalidRound(round)) {
            throw new IllegalArgumentException("Round 는 zero 인덱스를 사용하기 때문에 0 ~ 9 사이의 값을 입력해주세요");
        }
        this.round = round;
    }

    public static Round from(int round) {
        return new Round(round);
    }

    private boolean isInvalidRound(int round) {
        return round < MIN_ROUND || round > MAX_ROUND;
    }

    public boolean isRoundEnd(List<Frame> frames) {
        return frames.get(round).isEnd();
    }

    public int getRound() {
        return round;
    }

    public Frame currentRound(List<Frame> frames) {
        return frames.get(round);
    }

    public void goNextRound() {
        round++;
    }
}
