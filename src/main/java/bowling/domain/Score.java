package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Score {

    private static final int PITCH_THRESHOLD = 2;

    private final List<Pin> pins = new ArrayList<>();

    public void add(final Pin pin) {

        validate(pin);
        pins.add(pin);
    }

    private void validate(final Pin pin) {

        if (!status().strikeOrSpare() && checkExceed(pin)) {
            throw new IllegalArgumentException("쓰러뜨릴 핀 갯수가 올바르지 않습니다.");
        }
    }

    private boolean checkExceed(final Pin pin) {

        return lastPin() + pin.count() > 10;
    }

    public int lastPin() {

        if (pins().isEmpty()) {
            return 0;
        }

        return getPin(pins.size() - 1);
    }

    public ScoreType status() {

        return Stream.of(ScoreType.values())
                .filter(scoreType -> scoreType.matches(this))
                .findFirst()
                .orElse(ScoreType.MISS);
    }

    public int pitches() {

        return pins.size();
    }

    public boolean frameEnd() {

        return pitches() == PITCH_THRESHOLD;
    }

    public int getPin(final int index) {

        return pins.get(index)
                .count();
    }

    public List<Pin> pins() {

        return Collections.unmodifiableList(pins);
    }

    public int calculate() {

        return this.getPin(this.pitches() - 2) + this.lastPin();
    }
}
