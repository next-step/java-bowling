package bowling.domain.bowl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bowls {

    public static final String DELIMITER = "|";
    public static final int MAX_BOWL_COUNT = 3;

    private final List<Bowl> bowls = new ArrayList<>();

    public Bowls() {
        bowls.add(new Bowl());
    }

    public void bowl(int numberOfPins) {
        Bowl bowl = findIncompleteBowl();
        bowl.bowl(numberOfPins);
        bonus(bowl);
    }

    private Bowl findIncompleteBowl() {
        return bowls.stream()
                .filter(Bowl::isIncomplete)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private void bonus(Bowl bowl) {
        if (bowl.isBonus()) {
            bowls.add(new Bowl());
        }
    }

    public boolean isCompleted() {
        return isAllCompleted() || isMaxBonusCount();
    }

    private boolean isAllCompleted() {
        return bowls.stream()
                .allMatch(Bowl::isCompleted);
    }

    private boolean isMaxBonusCount() {
        return bowls.stream()
                .map(Bowl::getBowlCount)
                .reduce(0, Integer::sum) == MAX_BOWL_COUNT;
    }

    public String format() {
        return bowls.stream()
                .filter(normalBowl -> !normalBowl.isNone())
                .map(Bowl::format)
                .collect(Collectors.joining(DELIMITER));
    }

}
