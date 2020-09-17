package bowling.domain.bowl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalBowls {

    public static final String DELIMITER = "|";
    public static final int MAX_BOWL_COUNT = 3;

    private final List<NormalBowl> normalBowls = new ArrayList<>();

    public NormalBowls() {
        normalBowls.add(new NormalBowl());
    }

    public void bowl(int numberOfPins) {
        NormalBowl normalBowl = findNormalBowl();
        normalBowl.bowl(numberOfPins);
        bonus(normalBowl);
    }

    private NormalBowl findNormalBowl() {
        return normalBowls.stream()
                .filter(normalBowl1 -> !normalBowl1.isCompleted())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private void bonus(NormalBowl normalBowl) {
        if (isStrikeOrSpare(normalBowl)) {
            normalBowls.add(new NormalBowl());
        }
    }

    private boolean isStrikeOrSpare(NormalBowl normalBowl) {
        return normalBowl.getNormalBowlResult().equals(NormalBowlResult.STRIKE) ||
                normalBowl.getNormalBowlResult().equals(NormalBowlResult.SPARE);
    }

    public boolean isCompleted() {
        return normalBowls.stream().allMatch(NormalBowl::isCompleted) ||
                normalBowls.stream().map(NormalBowl::getBowlCount).reduce(0, Integer::sum) == MAX_BOWL_COUNT;
    }

    @Override
    public String toString() {
        return normalBowls.stream()
                .filter(normalBowl -> !normalBowl.isNone())
                .map(normalBowl -> NormalBowlResult.getType(normalBowl).format(normalBowl))
                .collect(Collectors.joining(DELIMITER));
    }

}
