package bowling.domain.frame;

public class FrameFactory {

    public static Frame getReadyFrame(Round round) {
        if (round.isLast()) {
            return FinalFrame.readyFrame();
        }

        return NormalFrame.readyFrame(round);
    }
}
