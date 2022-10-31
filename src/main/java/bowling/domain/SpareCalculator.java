package bowling.domain;

import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.List;

public class SpareCalculator implements ScoreCalculator {

    @Override
    public int calculate(List<BowlingRound> rounds) {
        return 0;
    }
}
