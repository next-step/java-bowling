package domain.status;

public class Gutter extends FirstBowlFinished {
    protected Gutter(int pin) {
        super(pin);
    }

    @Override
    public String toString() {
        return "-|";
    }
}