package bowling.domain;

import bowling.strategy.FallenPinCalculateStrategy;
import bowling.type.BowlingScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneralScore {
    private static final int MAX_PIN_COUNT = 10;

    private final List<Integer> scores;
    private FallenPinCalculateStrategy strategy;

    public GeneralScore(FallenPinCalculateStrategy strategy) {
        this.strategy = strategy;
        this.scores = new ArrayList<>();
        scores.add(strategy.calculate(MAX_PIN_COUNT));
    }

    public GeneralScore(List<Integer> scores) {
        this.scores = scores;
    }

    public boolean isStrike(){
        return scores.get(0) == 10;
    }

    public void next(){
        int maxNum = MAX_PIN_COUNT - getFirst();
        int newScore = strategy.calculate(maxNum);
        scores.add(newScore);
    }

    public void next(int maxNum){
        scores.add(strategy.calculate(maxNum));
    }

    public BowlingScore getBowlingScore() {
        if(isStrike()) return BowlingScore.STRIKE;
        if(scores.size() == 1) return BowlingScore.NONE;
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

    public int getLatest(){
        return scores.get(scores.size() - 1);
    }

    public int sum(){
        return scores.stream()
                .mapToInt(i -> i)
                .sum();
    }

    public int size(){
        return scores.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralScore that = (GeneralScore) o;
        return Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
