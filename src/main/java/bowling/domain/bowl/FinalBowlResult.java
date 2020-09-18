package bowling.domain.bowl;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalBowlResult {

    public static final String DELIMITER = "|";
    public static final int MAX_BOWL_COUNT = 3;

    private final LinkedList<BowlResult> bowlResults = new LinkedList<>();

    public void add(BowlResult bowlResult) {
        bowlResults.add(bowlResult);
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

    private boolean isCompleted() {
        return bowlResults.getLast().isCompleted();
    }

    private boolean isBonus() {
        return bowlResults.getLast().isBonus();
    }

    public String format() {
        return bowlResults.stream()
                .filter(normalBowl -> !normalBowl.isNone())
                .map(BowlResult::format)
                .collect(Collectors.joining(DELIMITER));
    }

}
