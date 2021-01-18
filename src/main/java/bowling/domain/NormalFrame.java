package bowling.domain;

public class NormalFrame extends BaseFrame {

    NormalFrame(int frameNo) {
        super(frameNo, new NormalPinMarker());
    }

    @Override
    public Frame createNext() {
        next = FrameFactory.createNextFrame(frameNo);
        return next;
    }

    @Override
    public FrameScore getScore() {
        FrameScore score = NormalFrameScoreFactory.create(pinMarker.getCountOfAllFallDownPins(), pinMarker.getState());
        if (score.hasRemainingAddition() && hasNext()) {
            next.addScoreTo(score);
        }

        try {
            return FrameScores.immutable(score);
        } catch (IllegalStateException e) {
            return FrameScore.unknown;
        }
    }

    @Override
    public FrameScore addScoreTo(FrameScore score) {
        pinMarker.markStream()
                .forEach( mark -> score.addScore(mark.getCountOfFallDownPins()));

        if (score.hasRemainingAddition() && hasNext()) {
            next.addScoreTo(score);
        }

        return score;
    }

}


