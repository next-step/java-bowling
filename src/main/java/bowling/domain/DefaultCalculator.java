package bowling.domain;

import java.util.List;

public class DefaultCalculator implements ScoreCalculator{
    @Override
    public int calculate(List<BowlingRound> rounds) {
        return 1;
    }
}
