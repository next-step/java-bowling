package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidPinStatusException;
import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;

public class Spare extends Ended {

    private final DownedPin firstPitch;
    private final DownedPin secondPitch;

    public Spare(DownedPin firstPitch, DownedPin secondPitch) {
        if (!firstPitch.isSpare(secondPitch)) {
            throw new InvalidPinStatusException();
        }

        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm(secondPitch);
    }

    @Override
    public Score calculateBaseScoreOfFrame() {
        return new Score(10, 1);
    }

    @Override
    public Score addBonus(Score originalScore) {
        Score bonusAddedScore = firstPitch.addToScore(originalScore);

        if (!bonusAddedScore.isFixed()) {
            bonusAddedScore = secondPitch.addToScore(bonusAddedScore);
        }

        return bonusAddedScore;
    }
}
