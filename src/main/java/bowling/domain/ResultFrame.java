package bowling.domain;

import bowling.domain.vo.Type;

public class ResultFrame {
    private final Type type;
    private final NormalFrame normalFrame;

    public ResultFrame(final Type type, final NormalFrame normalFrame) {
        this.type = type;
        this.normalFrame = normalFrame;
    }
}
