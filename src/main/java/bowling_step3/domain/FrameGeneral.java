package bowling_step3.domain;

public class FrameGeneral extends FrameMutual implements Frame {
    public FrameGeneral() {
        super();
    }

    public FrameGeneral(Frame frame) {
        super(frame);
    }

    protected Scores evaluateScore(Scores scores) {
        return scores;
    }
}
