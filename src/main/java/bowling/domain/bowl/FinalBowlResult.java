package bowling.domain.bowl;

import bowling.domain.NumberOfPin;
import bowling.domain.frame.FinalFrame;
import bowling.domain.score.DefaultFinalScore;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalBowlResult {

    public static final String DELIMITER = "|";
    public static final int MAX_BOWL_COUNT = 3;

    private final LinkedList<BowlResult> bowlResults = new LinkedList<>();

    public void add(BowlResult bowlResult) {
        bowlResults.add(bowlResult);
    }

    public boolean isNone() {
        return bowlResults.isEmpty();
    }

    public boolean isEnd() {
        if (bowlResults.isEmpty()) {
            return false;
        }
        return isMaxBonusCount() || (isCompleted() && !isBonus());
    }

    private boolean isMaxBonusCount() {
        return getTotalBowlCount() == MAX_BOWL_COUNT;
    }

    private Integer getTotalBowlCount() {
        return bowlResults.stream()
                .map(BowlResult::getBowlCount)
                .reduce(0, Integer::sum);
    }

    public boolean isCompleted() {
        return bowlResults.getLast().isCompleted();
    }

    private boolean isBonus() {
        return bowlResults.getLast().isBonus();
    }

    public Score getScore(FinalFrame finalFrame) {
        return new DefaultFinalScore(finalFrame);
    }

    public String format() {
        return bowlResults.stream()
                .filter(normalBowl -> !normalBowl.isNone())
                .map(BowlResult::format)
                .collect(Collectors.joining(DELIMITER));
    }

    public int getTotalNumberOfPin() {
        return bowlResults.stream()
                .map(bowlResult -> bowlResult.getTotalNumberOfPin())
                .reduce(0, Integer::sum);
    }

    public boolean isStrike() {
        return !isNone() && bowlResults.getFirst().isStrike();
    }

    public boolean checkSpareBonus() {
        return getTotalBowlCount() >= 1;
    }

    public int getSpareBonus() {
        List<NumberOfPin> numberOfPins = flatBowlResults();
        return numberOfPins.get(0).getNumberOfPin();
    }

    public boolean checkStrikeBonus() {
        return getTotalBowlCount() >= 2;
    }

    public int getStrikeBonus() {
        List<NumberOfPin> numberOfPins = flatBowlResults();
        return numberOfPins.get(0).getNumberOfPin() + numberOfPins.get(1).getNumberOfPin();
    }

    private List<NumberOfPin> flatBowlResults() {
        return bowlResults.stream()
                .map(bowlResult -> bowlResult.getNumberOfPins())
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
    }

}
