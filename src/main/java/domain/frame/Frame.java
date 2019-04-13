package domain.frame;

import domain.pin.Pin;
import domain.status.Ready;
import domain.status.Status;
import domain.status.Statuses;

import java.util.stream.Collectors;

import static domain.frame.Frames.LAST_FRAME;

public abstract class Frame {
    private final int number;
    protected Statuses statuses = new Statuses();
    protected Frame next;

    public Frame(int number, Pin pin, Frame previous) {
        this.number = number;
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

    public int getPin(int i) {
        return getStatus(i).getCurrentPin();
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

    private void addNextStatus(Pin pin) {
        statuses.add(getLastStatus().getNext(pin));
    }

    public boolean isLastFrame() {
        return number >= LAST_FRAME;
    }

    public Frame bowl(Pin pin) {
        addNextStatus(pin);
        return this;
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

    public int getScore() {
        return statuses.getStream()
                .mapToInt(Status::getCurrentPin)
                .sum();
    }

    @Override
    public String toString() {
        return statuses.getStream()
                .map(Status::toString)
                .collect(Collectors.joining("|"));
    }
}