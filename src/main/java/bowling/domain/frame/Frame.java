package bowling.domain.frame;

import bowling.dto.FrameEnumDto;
import bowling.dto.PinsDto;

public class Frame {
    private FrameState state;

    public Frame() {
        state = new InitialFrameState(new Pins(), 0);
    }

    private Frame(FrameState state) {
        this.state = state;
    }

    void setState(FrameState state) {
        this.state = state;
    }

    public void addPin(Pin pin) {
        state.addPin(pin);
        state.updateState(this);
    }

    public Frame generateNextFrame() {
        Pins pins = state.getPins();
        FrameState nextState = new InitialFrameState(pins, pins.size());
        return new Frame(nextState);
    }

    public boolean hasScore() {
        return state.hasScore();
    }

    public int getCountOfPins() {
        return state.getCountOfPins();
    }

    public FrameEnum getFrameEnum() {
        return state.getFrameEnum();
    }

    public FrameEnumDto exportFrameDto() {
        return new FrameEnumDto(getFrameEnum());
    }

    public PinsDto exportPinsDto() {
        return state.getPins().exportPinsDto();
    }
}
