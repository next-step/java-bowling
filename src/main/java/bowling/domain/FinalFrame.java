package bowling.domain;

import java.util.Map;

public class FinalFrame {
    private final Map<Round, BowlingPins> record;

    public FinalFrame(final Map<Round, BowlingPins> record) {
        this.record = record;
    }
}
