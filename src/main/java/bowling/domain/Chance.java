package bowling.domain;

public class Chance {
    private final Pin pin;
    private final Status status;

    public Chance(Pin pin, Status status) {
        this.pin = pin;
        this.status = status;
    }

    public Pin pin() {
        return pin;
    }

    public String chance() {
        if (status == Status.NONE || status == Status.MISS) {
            return String.valueOf(pin.pin());
        }
        return status.symbol();
    }

}
