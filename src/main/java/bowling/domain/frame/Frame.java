package bowling.domain.frame;

import bowling.domain.Rolls;
import bowling.dto.FrameDto;

public class Frame {
    private final int rollIndex;
    private FrameState state = InitialFrameState.getInstance();

    private Frame(int rollIndex) {
        this.rollIndex = rollIndex;
    }

    public static Frame of(Rolls rolls) {
        Frame context = new Frame(rolls.size() - 1);
        context.update(rolls);
        return context;
    }

    void setState(FrameState state) {
        this.state = state;
    }

    int getRollIndex() {
        return rollIndex;
    }

    public int getScore(Rolls rolls) {
        return state.getScore(this, rolls);
    }

    public boolean hasScore(Rolls rolls) {
        return state.hasScore(this, rolls);
    }

    public void update(Rolls rolls) {
        state.update(this, rolls);
    }

    public FrameEnum getFrameEnum() {
        return state.getFrameEnum();
    }

    public FrameDto exportFrameDto() {
        return new FrameDto(getFrameEnum());
    }
}
