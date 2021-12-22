package bowling.domain.frame;

import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int BONUS_BALL = 3;
    private static final int NORMAL_BALL = 2;
    private static final int MAX_PIN = 10;

    private int ballCount;
    private final List<Pin> pin = new ArrayList<>();

    public FinalFrame() {
        this.ballCount = NORMAL_BALL;
    }

    @Override
    public boolean isBallCount() {
        if (ballCount == NORMAL_BALL) {
            return pin.size() < NORMAL_BALL;
        }
        return pin.size() < BONUS_BALL;
    }

    @Override
    public void addPin(int pin) {
        this.pin.add(new Pin(pin));
        if (strike(pin) || spare()) {
            ballCount = BONUS_BALL;
        }
    }

    @Override
    public boolean strike(int pin) {
        return pin == MAX_PIN;
    }

    @Override
    public int numberOfBall() {
        return pin.size();
    }

    private boolean spare() {
        if (pin.size() == 2) {
            return (pin.get(0).value() + pin.get(1).value()) == MAX_PIN;
        }
        return false;
    }

    @Override
    public int fallenPin(int ballIndexNumber) {
        return pin.get(ballIndexNumber).value();
    }

    public int getBallCount() {
        return ballCount;
    }
}
