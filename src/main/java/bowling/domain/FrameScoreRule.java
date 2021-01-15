package bowling.domain;

public interface FrameScoreRule {

    int getAdditionCount();

    default FrameScore createBaseScore(Frame frame){
        return FrameScore.of( getAdditionCount(), frame.getCountListOfFallDownPins());
    }
}
