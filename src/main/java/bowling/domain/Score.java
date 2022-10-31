package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Score {

    private final List<Pin> pins = new ArrayList<>();

    public void addPin(Pin pin) {
        validate(pin);
        pins.add(pin);
    }

    private void validate(Pin pin) {
        if (!(status().isKnockedDowned()) &&
                lastPinNumber() + pin.count() > 10) {
            throw new IllegalArgumentException("쓰러뜨릴 핀 갯수가 올바르지 않습니다.");
        }
    }

    public int lastPinNumber() {
        if (pins().size() == 0) {
            return 0;
        }

        return pinNumber(pins.size() - 1);
    }

    public ScoreType status() {
        return Stream.of(ScoreType.values())
                .filter(scoreType -> scoreType.matches(this))
                .findFirst().orElse(ScoreType.MISS);
    }

    public int pinsSize() {
        return pins.size();
    }

    public int pinNumber(int index) {
        return pins.get(index).count();
    }

    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }
}
