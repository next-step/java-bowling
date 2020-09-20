package bowling.domain.bowl;

import bowling.domain.NumberOfPin;
import bowling.domain.frame.FinalFrame;
import bowling.domain.score.DefaultFinalScore;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.bowl.BowlResult.*;

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

    public boolean isStrike() {
        return !isNone() && bowlResults.getFirst().isStrike();
    }

    public boolean isCompleted() {
        return !isNone() && bowlResults.getLast().isCompleted();
    }

    private boolean isBonus() {
        return !isNone() && bowlResults.getLast().isBonus();
    }

    public boolean isEnd() {
        return isMaxBonusCount() || (isCompleted() && !isBonus());
    }

    private boolean isMaxBonusCount() {
        return getTotalBowlCount() == MAX_BOWL_COUNT;
    }

    private int getTotalBowlCount() {
        return bowlResults.stream()
                .map(BowlResult::getBowlCount)
                .reduce(0, Integer::sum);
    }

    public int getTotalNumberOfPin() {
        return bowlResults.stream()
                .map(bowlResult -> bowlResult.getTotalNumberOfPin())
                .reduce(0, Integer::sum);
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

    public boolean checkSpareBonus() {
        return getTotalBowlCount() >= FIRST_BOWL_COUNT;
    }

    public int getSpareBonus() {
        List<NumberOfPin> numberOfPins = flatBowlResults();
        return numberOfPins.get(FIRST_NUMBER_OF_PINS_INDEX).getNumberOfPin();
    }

    public boolean checkStrikeBonus() {
        return getTotalBowlCount() >= SECOND_BOWL_COUNT;
    }

    public int getStrikeBonus() {
        List<NumberOfPin> numberOfPins = flatBowlResults();
        return numberOfPins.get(FIRST_NUMBER_OF_PINS_INDEX).getNumberOfPin() + numberOfPins.get(SECOND_NUMBER_OF_PINS_INDEX).getNumberOfPin();
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
