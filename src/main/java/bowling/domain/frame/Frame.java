package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import java.util.Objects;

public class Frame {

    private final FrameNumber frameNumber;
    private final Pins frameDownPins;

    public static Frame of(FrameNumber frameNumber, Pins frameDownPins) {
        return new Frame(frameNumber, frameDownPins);
    }

    private Frame(FrameNumber frameNumber, Pins frameDownPins) {
        this.frameNumber = frameNumber;
        this.frameDownPins = frameDownPins;
    }

    public void roll(int numberOfDownPin, Scores scores) {
        this.frameDownPins.down(numberOfDownPin);
        scores.updateScore(frameNumber, Score.create(frameDownPins.getDownPins(), ScoreType.READY));
    }

    public boolean hasTurn() {
        return this.frameDownPins.hasTurn();
    }

    public ScoreType scoreType() {
        return this.frameDownPins.getScoreType();
    }

    public FrameResult getFrameResult(Scores scores) {
        return new FrameResult(this.frameDownPins.getDownPins(), this.frameDownPins.getScoreType(), frameScore(scores));
    }

    public Score frameScore(Scores scores) {
        if (isNotCalEnd(scores)) {
            scores.updateScore(frameNumber, frameDownPins.frameScore(frameNumber, scores));
        }
        return scores.getFromIndex(frameNumber);
    }

    private boolean isNotCalEnd(Scores scores) {
        return !Objects.equals(scores.getFromIndex(frameNumber).getScoreType(), ScoreType.NORMAL);
    }
}
