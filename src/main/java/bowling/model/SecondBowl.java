package bowling.model;

public class SecondBowl extends FrameState {

    private final Pins first;
    private final Pins second;

    public SecondBowl(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

    @Override
    boolean isFinished() {
        return true;
    }
}
