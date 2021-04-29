package bowling.domain.status;

import bowling.domain.Pitches;

public class Spare extends Symbol implements Status {
    private static final int PINS = 10;
    private static final int PITCH_INDEX = 2;

    private final String keyword;

    public Spare(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.sum() == PINS && pitches.count() == PITCH_INDEX;
    }

    @Override
    public String keyword() {
        return keyword;
    }

    @Override
    public String display(Pitches pitches) {
        return pitches.firstPitch() + DELIMITER + SPARE_SYMBOL;
    }
}
