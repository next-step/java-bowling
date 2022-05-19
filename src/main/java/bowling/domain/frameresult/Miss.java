package bowling.domain.frameresult;

import bowling.domain.frame.Frame;

import java.util.Optional;

public class Miss implements FrameResult {

    private final int firstNo;
    private final int secondNo;

    public Miss(int firstNo, int secondNo) {
        this.firstNo = firstNo;
        this.secondNo = secondNo;
    }

    @Override
    public Optional<Integer> score(Frame nextFrame) {
        return Optional.of(firstNo + secondNo);
    }
}
