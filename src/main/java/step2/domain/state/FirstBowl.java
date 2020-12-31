package step2.domain.state;

import step2.domain.Pitch;
import step2.domain.Score;

public class FirstBowl extends Running {

    private Pitch firstPitch;

    public FirstBowl(Pitch firstPitch) {
        this.firstPitch = firstPitch;
    }

    @Override
    public State bowl(int fallingPins) {
        int totalScore = firstPitch.getScore() + fallingPins;
        Pitch secondPitch = Pitch.from(fallingPins);

        if (totalScore == Pitch.MAX_SCORE) {
            return new Spare(firstPitch, secondPitch);
        }

        return new Miss(firstPitch, secondPitch);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

}
