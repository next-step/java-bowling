package bowling.domain;

public class FinalFrame extends BaseFrame {

    FinalFrame(int frameNo) {
        super(frameNo, new FinalPinMarker());
    }

    @Override
    public Frame createNext() {
        return null;
    }

    @Override
    public FrameScore getScore() {
        FrameScore score = FinalFrameScoreFactory.create(pinMarker.getCountOfAllFallDownPins(), pinMarker.getState());
        try {
            return FrameScores.immutable(score);
        } catch ( IllegalStateException e ){
            return FrameScore.unknown;
        }
    }

    @Override
    public FrameScore addScoreTo(FrameScore score) {
        pinMarker.markStream()
                .forEach( mark -> score.addScore(mark.getCountOfFallDownPins()));
        return score;
    }


}
