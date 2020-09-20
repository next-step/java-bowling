package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

import java.util.LinkedList;
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
        return bowlResults.stream()
                .map(BowlResult::getBowlCount)
                .reduce(0, Integer::sum) == MAX_BOWL_COUNT;
    }

    public boolean isCompleted() {
        return bowlResults.getLast().isCompleted();
    }

    private boolean isBonus() {
        return bowlResults.getLast().isBonus();
    }

    public Score getScore(Frame frame) {
        return new NormalScore(frame);
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

    public int getFirstNumberOfPin() {
        return bowlResults.getFirst().getFirstNumberOfPin();
    }

    public boolean isStrike() {
        return bowlResults.getFirst().isStrike();
    }

}
