package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.exception.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pins {
    private static final int FIRST_INDEX = 0;
    private static final int FORMAL_SIZE = 2;
    private static final int MAX_SIZE = 3;
    private static final int OPERATION = 1;
    private static final String EXCESS_PITCH_COUNT_ERROR = "더이상 투구할 수 없습니다.";
    private static final String EXCESS_PIN_COUNTS_ERROR = "한 노멀 프레임에서 쓰러트릴 수 있는 핀의 갯수를 초과했습니다.";

    private List<Pin> pins;

    private Pin firstPin;
    private Pin secondPin;
    private Pin bonusPin;

    public Pins(Pin firstPin) {
        this.firstPin = firstPin;
    }

    public Pins(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public Pins(Pin firstPin, Pin secondPin, Pin bonusPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        this.bonusPin = bonusPin;
    }

    public static Pins normalPins(List<Integer> pins) {
        if (pins.size() >= 1 && pins.get(0) == 10) {
            return new Pins(new Pin(10), null, null);
        }
        if (pins.size() >= 2 && pins.get(0) + pins.get(1) == 10) {
            return new Pins(new Pin(pins.get(0)), new Pin(pins.get(1)), null);
        }
        if (pins.size() >= 2 && pins.get(0) + pins.get(1) <= 10) {
            return new Pins(new Pin(pins.get(0)), new Pin(pins.get(1)), null);
        }
        return null;
    }

    public static Pins finalPins(List<Integer> pins) {
        if (pins.get(0) + pins.get(1) < 10) {
            return new Pins(new Pin(pins.get(0)), new Pin(pins.get(1)), null);
        }
        if (pins.get(0) == 10 || pins.get(0) + pins.get(1) == 10) {
            return new Pins(new Pin(pins.get(0)), new Pin(pins.get(1)), new Pin(pins.get(2)));
        }
        return null;
    }


    public Pins(final List<Integer> pins) {
        if (pins.size() > MAX_SIZE) {
            throw new BusinessException(EXCESS_PITCH_COUNT_ERROR);
        }
        this.pins = pins.stream()
                .map(Pin::new)
                .collect(Collectors.toList());
    }

    public boolean isEnd(final FrameNumber frameNumber) {
        if (pins.isEmpty()) {
            return false;
        }
        if (!frameNumber.isLastNumber()) {
            return isStrike() || isSameSize(FORMAL_SIZE);
        }
        return isSameSize(MAX_SIZE) || (!isStrike() && !isSpare() && isSameSize(FORMAL_SIZE));
    }

    private boolean isSameSize(int size) {
        return pins.size() == size;
    }

    private boolean isStrike() {
        return pins.size() < MAX_SIZE
                && pins.get(FIRST_INDEX).equals(new Pin(Pin.MAX_PINS));
    }

    public boolean isSpare() {
        return isSameSize(FORMAL_SIZE) && sum() == Pin.MAX_PINS;
    }

    public Pins pitch(final int countOfPins, final FrameNumber frameNumber) {
        if (isEnd(frameNumber)) {
            throw new BusinessException(EXCESS_PITCH_COUNT_ERROR);
        }

        if (!frameNumber.isLastNumber() && isOverPins(countOfPins)) {
            throw new BusinessException(EXCESS_PIN_COUNTS_ERROR);
        }
        pins.add(new Pin(countOfPins));
        return this;
    }

    private boolean isOverPins(final int countOfPins) {
        return sum() + countOfPins > Pin.MAX_PINS;
    }

    public String result() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = FIRST_INDEX; index < pins.size(); index++) {
        }
        return stringBuilder.toString();
    }

    public int sum() {
        return pins.stream().map(Pin::value).reduce(0, Integer::sum);
    }

}
