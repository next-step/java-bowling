package bowling.domain.status;

public class Open extends Finish {
    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }

    @Override
    public boolean isOpen() {
        return true;
    }
}
