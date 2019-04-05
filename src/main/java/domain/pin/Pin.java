package domain.pin;

import domain.status.Ready;
import domain.status.Status;

public class Pin {
    public static final int MAXIMUM_PINS = 10;
    public static final int MINIMUM_PINS = 0;

    private int pin;
    private Status status;

    public Pin(int pin) {
       this(pin, new Ready(0).getNext(pin));
    }

    public Pin(int pin, Status status) {
        if(pin > MAXIMUM_PINS) {
            throw new IllegalArgumentException(MAXIMUM_PINS + " 이하의 숫자를 입력하세요");
        }

        if(pin < MINIMUM_PINS) {
            throw new IllegalArgumentException(MAXIMUM_PINS + " 이상의 숫자를 입력하세요");
        }

        this.pin = pin;
        this.status = status;
    }

    public int getPin() {
        return pin;
    }

    public Status getStatus() {
        return status;
    }
}