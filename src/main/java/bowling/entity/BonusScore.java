package bowling.entity;

import java.util.Objects;
import java.util.Optional;

public class BonusScore {

    private final Pitch nextFirstPitch;
    private final Pitch nextSecondPitch;

    public BonusScore(Pitch nextFirstPitch, Pitch nextSecondPitch) {
        throwIfAnyNull(nextFirstPitch, nextSecondPitch);
        this.nextFirstPitch = nextFirstPitch;
        this.nextSecondPitch = nextSecondPitch;
    }

    private void throwIfAnyNull(Pitch nextFirstPitch, Pitch nextSecondPitch) {
        if (Objects.isNull(nextFirstPitch) || Objects.isNull(nextSecondPitch)) {
            throw new IllegalArgumentException("보너스 점수 계산을 위한 투구를 모두 입력해주세요.");
        }
    }

    public Optional<Score> getBonusScore(FrameResult frameResult) {

        if (Objects.isNull(nextFirstPitch) && Objects.isNull(nextSecondPitch)) {
            return Optional.empty();
        }

        if (frameResult == FrameResult.STRIKE) {
            return getBonusScoreWhenStrike();
        }

        if (frameResult == FrameResult.SPARE) {
            return getBonusScoreWhenSpare();
        }

        return Optional.empty();
    }

    private Optional<Score> getBonusScoreWhenStrike() {

        if (nextFirstPitch.getFrameResult() == FrameResult.STRIKE) {
            return nextSecondPitch.getFirstScore()
                    .map(score -> score.plus(Score.perfect()));
        }

        return nextFirstPitch.getTotalScore();
    }

    private Optional<Score> getBonusScoreWhenSpare() {
        return nextFirstPitch.getTotalScore();
    }

}
