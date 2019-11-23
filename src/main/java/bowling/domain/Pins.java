package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pins {
    public static final int FIRST_BALL_LIMIT_INDEX = 1;
    public static final int DEFAULT_BALL_SIZE = 2;
    public static final int LAST_FRAME_BALL_SIZE = 3;
    public static final int NOT_ADD_ABLE_PINT_COUNT = 0;
    public static final String IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE = "한 프레임의 세번째 투구는 마지막 프레임에서 스트라이크 또는 스페어일때 가능 합니다.";
    public static final String IS_OVER_FLOW_BALL_COUNT = "마지막 프레임을 제외한 프레임에서는 최대 두번만 투구 할 수 있습니다.";
    public static final String IS_OVER_FLOW_PIN_COUNT = "마지막 프레임을 제외한 프레임에서는 10개 핀만 쓰러트릴 수 있습니다.";
    private List<Pin> Pins;

    public Pins(List<Pin> Pins) {
        this.Pins = Pins;
    }

    public Pins(boolean isLastFrame) {
        Pins = new ArrayList<>();
        int size = isLastFrame ? LAST_FRAME_BALL_SIZE : DEFAULT_BALL_SIZE;
        for (int i = 0; i < size; i++) {
            Pins.add(new Pin());
        }
    }

    public void fallDown(int pin) {
        checkAddAble(pin);
        Pin nextPin = this.Pins.stream()
                .filter(Pin::isNotFallDown)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        nextPin.fallDown(pin);
    }

    private void checkAddAble(int pin) {
        boolean isLastFrame = isLastFrame();
        if (isLastFrame && isThirdBall() && isNotAddAbleThirdBall()) {
            throw new IllegalArgumentException(IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE);
        }

        if (!isLastFrame && isThirdBall()) {
            throw new IllegalArgumentException(IS_OVER_FLOW_BALL_COUNT);
        }

        if (!isLastFrame && isOverFlowPinCount(pin)) {
            throw new IllegalArgumentException(IS_OVER_FLOW_PIN_COUNT);
        }
    }

    private boolean isLastFrame() {
        return this.Pins.size() == LAST_FRAME_BALL_SIZE;
    }

    private boolean isThirdBall() {
        return fallDownSize() == DEFAULT_BALL_SIZE;
    }

    private boolean isNotAddAbleThirdBall() {
        return !isAddAbleThirdBall();
    }

    private boolean isAddAbleThirdBall() {
        return isStrike() || isSpare();
    }

    private boolean isOverFlowPinCount(int pin) {
        return Pin.isOverFlowPinCount(score() + pin);
    }

    public boolean isStrike() {
        return Pins.stream()
                .limit(FIRST_BALL_LIMIT_INDEX)
                .map(Pin::isStrike)
                .findFirst()
                .orElse(false);
    }

    public boolean isSpare() {
        int sum = Pins.stream()
                .limit(DEFAULT_BALL_SIZE)
                .mapToInt(Pin::getPin)
                .sum();
        return sum == Pin.ALL_PIN_COUNT;
    }

    public int score() {
        return Pins.stream()
                .filter(Pin::isFallDown)
                .mapToInt(Pin::getPin)
                .sum();
    }

    public int addAblePinCount(boolean isLastFrame) {
        if (isLastFrame && isAddAbleAllPins()) {
            return Pin.ALL_PIN_COUNT;
        }

        if (fallDownSize() == DEFAULT_BALL_SIZE) {
            return NOT_ADD_ABLE_PINT_COUNT;
        }

        return Pin.leftPinCount(score());
    }

    private boolean isAddAbleAllPins() {
        long ballsSize = fallDownSize();
        return (ballsSize < DEFAULT_BALL_SIZE && isStrike()) ||
                (ballsSize == DEFAULT_BALL_SIZE && isAddAbleThirdBall());
    }

    private long fallDownSize() {
        return Pins.stream()
                .filter(Pin::isFallDown)
                .count();
    }

    public List<Pin> unmodifiableBalls() {
        return Collections.unmodifiableList(this.Pins);
    }

    public boolean isNotSameSize(int size) {
        return Pins.size() != size;
    }
}
