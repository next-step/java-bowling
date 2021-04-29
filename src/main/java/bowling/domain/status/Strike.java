package bowling.domain.status;

import bowling.domain.Pitches;

public class Strike extends Symbol implements Status {
    private static final int PINS = 10;
    private static final int PITCH_INDEX = 1;

    private final String keyWord;

    public Strike(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean condition(Pitches pitches) {
        return pitches.sum() == PINS && pitches.count() == PITCH_INDEX;
    }

    @Override
    public String keyword() {
        return keyWord;
    }

    @Override
    public String display(Pitches pitches) {
        return STRIKE_SYMBOL;
    }
}
