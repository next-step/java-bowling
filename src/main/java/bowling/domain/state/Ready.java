package bowling.domain.state;

public class Ready extends Playing {
    private static final int MAX_FALLEN_PINS = 10;
    private static final String EMPTY_FRAME = " ";

    @Override
    public State bowl(int pins) {
        if (pins == MAX_FALLEN_PINS) {
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
