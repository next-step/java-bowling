package bowling.domain.frame;

import bowling.dto.FrameEnumDto;
import bowling.dto.PinsDto;
import bowling.dto.ScoreDto;

public class Frame {
    private final int accumulated;
    private FrameState state;

    public Frame() {
        accumulated = 0;
        state = new InitialFrameState(new Pins(), 0);
    }

    private Frame(int accumulated, FrameState state) {
        this.accumulated = accumulated;
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
        return new Frame(
                accumulated + state.getCountOfPins()
                , new InitialFrameState(pins, pins.size()));
    }

    public boolean hasScore() {
        return state.hasScore();
    }

    public FrameEnum getFrameEnum() {
        return state.getFrameEnum();
    }

    public FrameEnumDto exportFrameDto() {
        return new FrameEnumDto(getFrameEnum());
    }

    public ScoreDto exportScoreDto() {
        return new Score(accumulated + state.getCountOfPins())
                .exportScoreDto();
    }

    public PinsDto exportPinsDto() {
        return state.getPins().exportPinsDto();
    }
}
