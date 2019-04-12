package domain.frame;

import domain.pin.Pin;
import domain.pin.Pins;
import domain.status.Ready;
import domain.status.Status;
import domain.status.Statuses;

import java.util.stream.Collectors;

import static domain.frame.Frames.LAST_FRAME;

public abstract class Frame {
    private final int number;
    protected Pins pins = new Pins();
    protected Statuses statuses = new Statuses();
    protected Frame next;

    public Frame(int number, Pin pin, Frame previous) {
        this.number = number;
        pins.add(pin);
        statuses.add(new Ready(getPreviousStatus(previous)).getNext(pin));
    }

    private Status getPreviousStatus(Frame previous) {
        if (previous != null) {
            return previous.getLastStatus();
        }

        return null;
    }

    public int getNumber() {
        return number;
    }

    public Pin getPin(int i) {
        return pins.get(i);
    }

    public int getPinsSize() {
        return pins.size();
    }

    public Status getStatus(int i) {
        return statuses.get(i);
    }

    public Status getLastStatus() {
        return statuses.getLastStatus();
    }

    public int getStatusesSize() {
        return statuses.size();
    }

    private void addPin(Pin pin) {
        pins.add(pin);
    }

    private void addNextStatus(Pin pin) {
        statuses.add(getLastStatus().getNext(pin));
    }

    public boolean isLastFrame() {
        return number >= LAST_FRAME;
    }

    public Frame bowl(Pin pin) {
        int pinSize = pins.size();

        try {
            addPin(pin);
            addNextStatus(pin);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            if (isRecentPinRemovable(pinSize)) {
                pins.removeRecent();
            }
        }
        return this;
    }

    private boolean isRecentPinRemovable(int pinSize) {
        return pins.size() > 0 && pins.size() > pinSize;
    }

    Frame createNextFrame(Pin pin) {
        if (number >= LAST_FRAME) {
            throw new IllegalStateException(String.format("프레임의 최대 개수는 %d개 입니다.", LAST_FRAME));
        }

        if (number == LAST_FRAME - 1) {
            return new LastFrame(pin, this);
        }

        return new NormalFrame(number + 1, pin, this);
    }

    public abstract boolean isFinished();

    public abstract boolean isScoreCalculationFinished();

    public abstract int getScore();

    @Override
    public String toString() {
        return statuses.getStream()
                .map(Status::toString)
                .collect(Collectors.joining("|"));
    }
}