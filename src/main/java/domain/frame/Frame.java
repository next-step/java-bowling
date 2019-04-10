package domain.frame;

import domain.pin.Pin;
import domain.pin.Pins;
import domain.status.Ready;
import domain.status.Status;
import domain.status.Statuses;
import util.StringUtils;

import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public abstract class Frame {
    private final int number;
    protected Pins pins = new Pins();
    protected Statuses statuses = new Statuses();
    protected Frame next;

    public Frame(int number, Pin pin) {
        this.number = number;
        pins.add(pin);
        statuses.add(new Ready().getNext(pin));
    }

    public Frame(int number, int accumulatedScore, Pin pin) {
        this.number = number;
        pins.add(pin);
        statuses.add(new Ready().getNext(pin));
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

    public int getPinScore(int i) {
        return pins.get(i).getPin();
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

    public Frame bowl(Pin pin) {
        addPin(pin);
        addNextStatus(pin);
        return this;
    }

    Frame createNextFrame(Pin pin) {
        if (number >= LAST_FRAME) {
            throw new IllegalStateException(String.format("프레임의 최대 개수는 %d개 입니다.", LAST_FRAME));
        }

        if (number == LAST_FRAME-1) {
            return new LastFrame(pin);
        }

        return new NormalFrame(number+1, pin );
    }

    public abstract boolean isFinished();
    public abstract boolean isBonusCalculationFinished(int left);
    public abstract int getScore();
    public abstract int getBonusScore(int left);

    @Override
    public String toString() {
        String status = statuses.getStream()
                .map(Status::toString)
                .collect(Collectors.joining("|"));

        return StringUtils.center(status, 6);
    }
}