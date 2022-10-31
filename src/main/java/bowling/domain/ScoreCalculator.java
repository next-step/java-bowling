package bowling.domain;

import java.util.List;

public interface ScoreCalculator {

    int calculate(List<BowlingRound> rounds);
}
