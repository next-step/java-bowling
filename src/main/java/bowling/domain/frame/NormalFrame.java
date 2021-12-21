package bowling.domain.frame;

import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private static final String FRAME_NUMBER_ERROR_MESSAGE = "error : 프레임은 최소 %d 최대 %d 입니다.";
    private static final String PIN_COUNT_ERROR_MESSAGE = "error : 핀은 %d개 입니다.";
    private static final int LAST = 9;
    private static final int START = 1;
    private static final int STRIKE_BALL = 1;
    private static final int NORMAL_BALL = 2;
    private static final int MAX_PIN = 10;

    private final int frame;
    private int ballCount;
    private List<Pin> pin = new ArrayList<>();

    public NormalFrame(int frame) {
        validFrame(frame);
        this.frame = frame;
        this.ballCount = NORMAL_BALL;
    }

    private void validFrame(int frame) {
        if (frame < START || frame > LAST) {
            throw new IllegalArgumentException(String.format(FRAME_NUMBER_ERROR_MESSAGE, START, LAST));
        }
    }

    private void validPin(int pin) {
        int pinSum = this.pin.get(0).value() + pin;
        if (this.pin.size() == NORMAL_BALL && pinSum > MAX_PIN) {
            throw new IllegalArgumentException(String.format(PIN_COUNT_ERROR_MESSAGE, MAX_PIN));
        }
    }

    @Override
    public boolean isBallCount() {
        if (ballCount == STRIKE_BALL) {
            return false;
        }
        return pin.size() < NORMAL_BALL;
    }

    @Override
    public void addPin(int pin) {
        this.pin.add(new Pin(pin));
        if (strike(pin)) {
            ballCount = STRIKE_BALL;
        }
        validPin(pin);
    }

    @Override
    public boolean strike(int pin) {
        return pin == MAX_PIN;
    }

    @Override
    public int numberOfBall() {
        return pin.size();
    }

    @Override
    public int fallenPin(int ballIndexNumber) {
        return pin.get(ballIndexNumber).value();
    }

    public int getBallCount() {
        return ballCount;
    }


}

