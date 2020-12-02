package bowling.domain;

import bowling.domain.frame.FrameContext;
import bowling.dto.FrameDto;

public class Frame {
    private final FrameContext context;

    private Frame(FrameContext context) {
        this.context = context;
    }

    public static Frame of(Rolls rolls) {
        FrameContext context = new FrameContext(rolls.size() - 1);
        context.update(rolls);
        return new Frame(context);
    }

    public void update(Rolls rolls) {
        context.update(rolls);
    }

    public FrameEnum getFrameEnum() {
        return context.getFrameEnum();
    }

    int getScore(Rolls rolls) {
        return context.getScore(rolls);
    }

    boolean hasScore(Rolls rolls) {
        return context.hasScore(rolls);
    }

    public FrameDto exportFrameDto() {
        return context.exportFrameDto();
    }
}
