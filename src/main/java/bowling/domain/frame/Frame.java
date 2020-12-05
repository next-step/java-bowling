package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.dto.FrameDto;

public class Frame {
    private final int pinsIndex;
    private FrameState state = InitialFrameState.getInstance();

    private Frame(int pinsIndex) {
        this.pinsIndex = pinsIndex;
    }

    public static Frame of(Pins pins) {
        Frame context = new Frame(pins.size() - 1);
        context.update(pins);
        return context;
    }

    void setState(FrameState state) {
        this.state = state;
    }

    int getPinsIndex() {
        return pinsIndex;
    }

    public int getScore(Pins pins) {
        return state.getScore(this, pins);
    }

    public boolean hasScore(Pins pins) {
        return state.hasScore(this, pins);
    }

    public void update(Pins pins) {
        state.update(this, pins);
    }

    public FrameEnum getFrameEnum() {
        return state.getFrameEnum();
    }

    public FrameDto exportFrameDto() {
        return new FrameDto(getFrameEnum());
    }
}
