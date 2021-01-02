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
        score = score.bowl(firstPitch.getScore());
        if (score.canCalculateScore()) {
            return score;
        }
        throw new IllegalArgumentException("점수를 계산할 수 없습니다.");
    }

    @Override
    public String getDesc() {
        return firstPitch.getDesc();
    }
}
