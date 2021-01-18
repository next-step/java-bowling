package bowling.domain;

public class NormalFrameScoreFactory {

    public static FrameScore create(int currentScore, PinMarkerState state) {
        if( !state.isCompleted() ){
            return FrameScore.unknown;
        }

        if( state instanceof NormalPinMarker.Strike ){
            return FrameScore.of(AdditionCounter.ofStrike(), currentScore );
        }

        if( state instanceof NormalPinMarker.Spare ){
            return FrameScore.of(AdditionCounter.ofSpare(), currentScore);
        }

        return FrameScore.of(currentScore);
    }
}
