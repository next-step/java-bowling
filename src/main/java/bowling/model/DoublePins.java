package bowling.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static bowling.model.DownPin.DOWN_ZERO;
import static java.util.stream.Collectors.toList;

public class DoublePins {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private List<DownPin> downDownPins;

    private DoublePins(List<DownPin> downDownPins) {
        this.downDownPins = downDownPins;
    }
    
    public static DoublePins valueOf(DownPin first) {
        return valueOf(first, first.saveRemaining());
    }
    
    public static DoublePins valueOf(DownPin... inputDownPins) {
        List<DownPin> downPins = Arrays.stream(inputDownPins)
                .collect(toList());

        validateMoreThenMax(downPins);
        return new DoublePins(downPins);
    }

    private static void validateMoreThenMax(List<DownPin> downPins) {
        downPins.stream().reduce(DownPin::sum);
    }

    public int getTotalCount() {
        return downDownPins.stream()
                .reduce(DOWN_ZERO, DownPin::sum)
                .count();
    }

    public DownPin get(int index) {
        return downDownPins.get(index);
    }

    List<DownPin> getDownDownPins() {
        return Collections.unmodifiableList(downDownPins);
    }
}
