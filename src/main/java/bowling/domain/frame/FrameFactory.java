package bowling.domain.frame;

import bowling.domain.Round;

public class FrameFactory {

    public static Frame getFrame(Round round) {
        if (round.isLast()) {
            return FinalFrame.readyFrame(round);
        }

        return NormalFrame.readyFrame(round);
    }
}
