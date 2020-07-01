package bowling.domain;

import java.util.Map;

public class NormalFrame {
    private final Map<Round, BowlingPins> record;

    public NormalFrame(final Map<Round, BowlingPins> record) {
        this.record = record;
    }
}
