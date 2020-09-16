package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameBowl {

    private final List<NumberOfPins> numberOfPins = new ArrayList<>();

    public void bowl(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
    }

}
