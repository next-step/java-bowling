package bowling.domain;

public class DefaultScoreRule implements FrameScoreRule {
    @Override
    public FrameScore apply(Frame frame) {
        return FrameScore.of(frame.getCountListOfFallDownPins());
    }
}
