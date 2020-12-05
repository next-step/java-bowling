package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.dto.FrameDto;

// TODO: Score 하고 합치기
public class Frame {
    private FrameState state;

    private Frame(int pinsIndex) {
        state = new InitialFrameState(pinsIndex);
    }

    public static Frame of(Pins pins) {
        Frame frame = new Frame(pins.size() - 1);
        frame.update(pins);
        return frame;
    }

    void setState(FrameState state) {
        this.state = state;
    }

    int getPinsIndex() {
        return state.getPinsIndex();
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
