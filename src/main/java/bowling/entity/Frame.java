package bowling.entity;

import java.util.Optional;

public class Frame implements Comparable<Frame> {

    private final int number;
    private final Pitch pitch;

    public Frame(int number) {
        this.number = number;
        pitch = new Pitch();
    }

    public FrameResult getFrameResult() {
        return pitch.getFrameResult();
    }

    public void bowl(Score score) {
        pitch.addScore(score);
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Optional<Score> getTotalScore(BonusScore bonusFrames) {

        if (isNotFinished()) {
            return Optional.empty();
        }

        Score totalScore = pitch.getTotalScore().get();

        Optional<Score> bonusScore = bonusFrames.getBonusScore(getFrameResult());

        if (getFrameResult() == FrameResult.STRIKE || getFrameResult() == FrameResult.SPARE) {
            return bonusScore.map(score -> score.plus(totalScore));
        }

        return Optional.of(totalScore);
    }

    public boolean isNotFinished() {
        return !pitch.isFinished();
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Frame o) {
        return Integer.compare(number, o.number);
    }
}
