package bowling.domain.frame;

import bowling.domain.FrameEnum;
import bowling.domain.Rolls;
import bowling.dto.FrameDto;

public class FrameContext {
    private final int rollIndex;
    private FrameState state = InitialFrameState.getInstance();

    public FrameContext(int rollIndex) {
        this.rollIndex = rollIndex;
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
