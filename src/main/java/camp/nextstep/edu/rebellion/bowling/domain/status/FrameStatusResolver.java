package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;

public class FrameStatusResolver {
    private static final int SPARE = 10;

    private FrameStatusResolver() {}

    public static FrameStatus resolve(Frame frame) {
        if (!frame.isStarted()) {
            return new NotStarted();
        }

        if (!frame.meetEnd()) {
            return new Playing(frame.getFirstScore());
        }

        return afterEnd(frame);
    }

    private static FrameStatus afterEnd(Frame frame) {
        if (frame.isStrike()) {
            return new Strike();
        }

        int first = frame.getFirstScore();
        int last = frame.getLastScore();
        int score = first + last;

        return SPARE == score ? new Spare(first, last) :
                new Miss(first, last);
    }
}
