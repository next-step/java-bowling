package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.frame.FrameScore;

public class FrameStatusResolver {

    private FrameStatusResolver() {}

    public static FrameStatus resolve(Frame frame) {
        if (!frame.isStarted()) {
            return new NotStarted();
        }

        if (!frame.meetEnd()) {
            return new Playing(frame.getFrameScore());
        }

        return afterEnd(frame.getFrameScore());
    }

    private static FrameStatus afterEnd(FrameScore score) {
        if (score.isStrike()) {
            return new Strike();
        }

        return score.isSpare() ? new Spare(score) :
                new Miss(score);
    }
}
