package bowling.domain;

import bowling.strategy.FallenPinCalculateStrategy;
import bowling.type.BowlingScore;

import java.util.ArrayList;
import java.util.List;

public class PinScore {
    private static final int MAX_PIN_COUNT = 10;

    private final List<Integer> scores;
    private final FallenPinCalculateStrategy strategy;

    public PinScore(FallenPinCalculateStrategy strategy) {
        this.strategy = strategy;
        this.scores = new ArrayList<>();
        scores.add(strategy.calculate(MAX_PIN_COUNT));
    }

    public boolean isStrike(){
        return scores.get(0) == 10;
    }

    public void next(){
        int maxNum = MAX_PIN_COUNT - scores.get(0);
        int newScore = strategy.calculate(maxNum);
        scores.add(newScore);
    }

    public BowlingScore getBowlingScore() {
        if(scores.get(0) + scores.get(1) == 10) return BowlingScore.SPARE;
        if(scores.get(1) == 0) return BowlingScore.GUTTER;
        return BowlingScore.MISS;
    }

    public int getFirst(){
        return scores.get(0);
    }

    public int getSecond(){
        return scores.get(1);
    }
}
