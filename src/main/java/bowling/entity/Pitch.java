package bowling.entity;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Pitch {

    private Score firstScore;
    private Score secondScore;

    public Pitch() {}

    public static Pitch of(Score firstScore, Score secondScore) {
        Pitch pitch = new Pitch();
        pitch.addScore(firstScore);
        pitch.addScore(secondScore);
        return pitch;
    }

    public static Pitch strike() {
        Pitch pitch = new Pitch();
        pitch.addScore(Score.perfect());
        return pitch;
    }

    public void addScore(Score score) {

        throwIfAllPitchNotEmpty();

        if (nonNull(firstScore) && isNull(secondScore)) {
            secondScore = score;
        }

        if (isNull(firstScore) && isNull(secondScore)) {
            firstScore = score;
        }

    }

    private void throwIfAllPitchNotEmpty() {
        if (isFinished()) {
            throw new IllegalStateException("모든 투구를 마쳤습니다.");
        }
    }

    public FrameResult getFrameResult() {

        if (!isFinished()) {
            return FrameResult.NOT_FINISHED;
        }

        if (firstScore.equals(Score.perfect())) {
            return FrameResult.STRIKE;
        }

        Score totalScore = firstScore.plus(secondScore);

        if (totalScore.equals(Score.perfect())) {
            return FrameResult.SPARE;
        }

        if (totalScore.equals(Score.zero())) {
            return FrameResult.GUTTER;
        }

        return FrameResult.MISS;
    }

    public Optional<Score> getFirstScore() {
        return Optional.ofNullable(firstScore);
    }

    public Optional<Score> getSecondScore() {
        return Optional.ofNullable(secondScore);
    }

    public boolean isFinished() {
        return isStrike() || Objects.nonNull(firstScore) && Objects.nonNull(secondScore);
    }

    private boolean isStrike() {
        return Score.perfect().equals(firstScore);
    }

    public Optional<Score> getTotalScore() {

        if (!isFinished()) {
            return Optional.empty();
        }

        if (isStrike()) {
            return Optional.of(firstScore);
        }

        return Optional.of(firstScore.plus(secondScore));
    }

}
