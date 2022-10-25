package bowling.domain;

import java.util.List;

public class FinalFrame extends Frame {
    private static final int BONUS_INCLUDED_BOWLING_TRIES = 3;

    FinalFrame(List<Bowling> bowlings) {
        super(bowlings);
    }

    public static Frame init() {
        return new FinalFrame(List.of(Bowling.init()));
    }

    @Override
    protected Frame create(List<Bowling> bowlings) {
        return new FinalFrame(bowlings);
    }

    @Override
    public boolean isFinished() {
        if (firstBowling().isStrike() || firstBowling().isSpare()) {
            return totalBowlingTries() == BONUS_INCLUDED_BOWLING_TRIES;
        }

        return firstBowling().isFinished();
    }

    private int totalBowlingTries() {
        return bowlings.stream()
                .mapToInt(Bowling::tries)
                .sum();
    }

    private Bowling firstBowling() {
        return bowlings.get(0);
    }
}
