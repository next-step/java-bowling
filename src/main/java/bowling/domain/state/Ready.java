package bowling.domain.state;

public class Ready extends Playing {
    private static final String EMPTY_FRAME = " ";

    public Ready() {
    }

    @Override
    public State bowl(Pin pins) {
        if (pins.isStrike()) {
            return new Strike();
        }
        return new NextReady(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return EMPTY_FRAME;
    }
}
