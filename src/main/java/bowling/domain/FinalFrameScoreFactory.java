package bowling.domain;

public class FinalFrameScoreFactory {

    public static FrameScore create(int currentScore, PinMarkerState state) {
        if (state.isCompleted()) {
            return FrameScore.of(currentScore);
        }
        return FrameScore.unknown;
    }
}
