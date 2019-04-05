package domain.frame;

import domain.pin.Pin;
import domain.pin.Pins;
import domain.status.Ready;
import domain.status.Status;

import java.util.stream.Collectors;

import static domain.frame.Frames.LAST_FRAME;

public abstract class Frame {
    protected final int number;
    protected final Pins pins = new Pins();

    public Frame(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean hasAnyPin() {
        return pins.getSize() > 0;
    }

    public Status getRecentStatus() {
        return pins.getRecentStatus();
    }

    public Pins getPins() {
        return pins;
    }

    public Frame bowl(int pin) {
        Frame frame = (isFinished()) ? createNextFrame() : this;
        Status status = (hasAnyPin()) ? getRecentStatus() : new Ready(0).getNext(pin);
        pins.add(new Pin(pin, status));
        return this;
    }

    private Frame createNextFrame() {
        if(number > LAST_FRAME) {
            throw new IllegalStateException(String.format("프레임의 최대 개수는 %d개 입니다.", LAST_FRAME));
        }

        if(number == LAST_FRAME - 1) {
            new LastFrame(number + 1);
        }

        return new NormalFrame(number + 1);
    }

    public abstract boolean isFinished();

    @Override
    public String toString() {
        return pins.getPins().stream()
                    .map(Pin::getStatus)
                    .map(status -> String.format("  %s  ", status.toString()))
                    .collect(Collectors.joining("|"));
    }
}