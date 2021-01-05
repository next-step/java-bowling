package bowling.domain;

public class FinalScoreRule implements FrameScoreRule {
    @Override
    public FrameScore apply(Frame frame) {
        return FrameScore.of(frame.getCountListOfFallDownPins());
    }
}
