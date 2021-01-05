package bowling.domain;

public class FrameScoreCalculatorFactory {

    public static FrameScoreCalculator create(boolean isFinal, Frame.Status status) {
        if( isFinal ) return FrameScoreCalculator.Final;
        if( status == Frame.Status.Strike ){
            return FrameScoreCalculator.Strike;
        }
        if( status == Frame.Status.Spare ){
            return FrameScoreCalculator.Spare;
        }
        return FrameScoreCalculator.Default;
    }
}
