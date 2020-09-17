package bowling.domain.bowl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalBowls {

    public static final String DELIMITER = "|";

    private final List<NormalBowl> normalBowls = new ArrayList<>();

    public NormalBowls() {
        normalBowls.add(new NormalBowl());
    }

    public void bowl(int numberOfPins) {
        normalBowls.get(0).bowl(numberOfPins);
    }

    public boolean isCompleted() {
        return normalBowls.get(0).isCompleted();
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, normalBowls.stream()
                .map(normalBowl -> NormalBowlResult.getType(normalBowl).format(normalBowl))
                .collect(Collectors.toList()));
    }

}
