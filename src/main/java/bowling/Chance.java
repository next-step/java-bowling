package bowling;

public class Chance {
    private final int order;
    private final Pin pin;
    private final Status status;

    public Chance(int order, Pin pin, Status status) {
        this.order = order;
        this.pin = pin;
        this.status = status;
    }

    public Pin pin() {
        return pin;
    }

    public String chance() {
        if (status == Status.NONE) {
            return String.valueOf(pin.pin());
        }
        return status.symbol();
    }
}
