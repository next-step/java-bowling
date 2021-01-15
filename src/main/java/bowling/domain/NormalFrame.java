package bowling.domain;

public class NormalFrame extends BaseFrame {

    NormalFrame(int frameNo) {
        super(frameNo, new NormalPinMarker());
    }

    @Override
    public Status getStatus() {
        if (!pinMarker.isStarted()) return Status.Ready;
        if (pinMarker.isStrike()) return Status.Strike;
        if (pinMarker.getCountOfMarks() == 1 ) return Status.SecondBowl;
        if (pinMarker.isSpare()) return Status.Spare;
        if (pinMarker.getCountOfAllFallDownPins() == 0) return Status.Gutter;
        return Status.Miss;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Frame createNext() {
        next = FrameFactory.createNextFrame(frameNo);
        return next;
    }

    @Override
    public FrameScore getScore() {
        try {
            FrameScore score = FrameScoreCalculatorFactory.create(isFinal(), getStatus()).calculate(this);
            if (score.hasRemainingAddition() && hasNext()) {
                next.addScoreTo(score);
            }
            return FrameScores.immutable(score);
        } catch ( IllegalStateException e ){
            return FrameScore.unknown;
        }
    }

    @Override
    public FrameScore addScoreTo(FrameScore score) {
        for( Integer pins : getCountListOfFallDownPins() ){
            score.addScore(pins);
        }

        if( score.hasRemainingAddition() && hasNext() ){
            next.addScoreTo(score);
        }

        return score;
    }


}


