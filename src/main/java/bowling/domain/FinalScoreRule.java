package bowling.domain;

public class FinalScoreRule implements FrameScoreRule {
    @Override
    public FrameScore apply(Frame frame) {
        if( frame.isEnd() ){
            return FrameScore.of(frame.getCountListOfFallDownPins());
        }
        return FrameScore.unknown;
    }
}
