package domain.status;

import domain.Pin;

public class Miss extends Status {
    private Pin pin;

    public Miss(Pin pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return String.valueOf(pin.getPin());
    }

}
