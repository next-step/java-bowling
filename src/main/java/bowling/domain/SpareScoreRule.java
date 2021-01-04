package bowling.domain;

public class SpareScoreRule implements FrameScoreRule {
    @Override
    public FrameScore apply(Frame frame) {
        if(frame.hasNext() && frame.next().getCountOfMarks() > 0 ){
            return FrameScore.of(10, frame.next().getCountListOfFallDownPins().get(0));
        }
        return FrameScore.unknown;
    }
}
