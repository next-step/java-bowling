package domain.status;

import domain.Pin;

public class Hit extends Status {
    private Pin pin;

    public Hit(Pin pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return String.valueOf(pin.getPin());
    }
}
