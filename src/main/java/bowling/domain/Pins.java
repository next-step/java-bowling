package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Pins {
    private List<Pin> pins;

    public Pins() {
        this(PinGenerator.generate());
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public int knockDown(int knockedDownPinCount) {
        List<Pin> pinList = pins.stream()
                .filter(Pin::isStanding)
                .limit(knockedDownPinCount)
                .collect(Collectors.toList());

        pinList.forEach(Pin::knockDown);

        return pinList.size();
    }

    public int knockDownCount() {
        return (int) pins.stream()
                .filter(Pin::isKnockedDown)
                .count();
    }

    public int standingPinCount() {
        return (int) pins.stream()
                .filter(Pin::isStanding)
                .count();
    }

    public void add(List<Pin> addPins) {
        pins.addAll(addPins);
    }
}
