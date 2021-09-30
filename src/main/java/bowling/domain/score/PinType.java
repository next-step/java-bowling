package bowling.domain.score;

import java.util.Arrays;
import java.util.List;

public enum PinType {

    SPARE("/", (pins, index) -> pins.size() == 2 && pins.get(0).sum(pins.get(1)) == Pin.of(10)),
    STRIKE("X", (pins, index) -> pins.get(index) == Pin.of(10)),
    GUTTER("-", (pins, index) -> pins.get(index) == Pin.of(0))
    ;

    private final String status;
    private final PinTypeStratgy stratgy;

    PinType(String status, PinTypeStratgy stratgy) {
        this.status = status;
        this.stratgy = stratgy;
    }

    public String status() {
        return status;
    }

    public static String pinToString(List<Pin> pins, int index) {
        return Arrays.stream(values())
            .filter(value -> value.stratgy.isScoreType(pins, index))
            .map(PinType::status)
            .findFirst()
            .orElse(pins.get(index).valueToString());
    }

}
