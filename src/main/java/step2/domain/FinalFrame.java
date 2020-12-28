package step2.domain;

import step2.domain.state.Miss;
import step2.domain.state.Spare;
import step2.domain.state.State;
import step2.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private final List<Pitch> pitches;

    public FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Frame init() {
        List<Pitch> pitches = new ArrayList<>(MAX_SIZE);
        return new FinalFrame(pitches);
    }

    @Override
    public void bowl(Pitch pitch) {
        if (getSize() == MIN_SIZE && !sameStrikeNumber()) {
            validateScore(pitch);
        }
        pitches.add(pitch);
    }

    @Override
    public boolean isFinish() {
        if (getSize() < NORMAL_FRAME_SIZE) {
            return false;
        }
        return isStrikeAndFinish() || isSpareAndFinish() || isMissAndFinish();
    }

    private boolean isStrikeAndFinish() {
        return isStrike() && isMaxSize();
    }

    private boolean isSpareAndFinish() {
        return isSpare() && isMaxSize();
    }

    private boolean isMissAndFinish() {
        return isMiss() && getSize() == NORMAL_FRAME_SIZE;
    }

    private boolean isStrike() {
        return sameStrikeNumber() || isLastStrike();
    }

    private boolean sameStrikeNumber() {
        int strikeNumber = (int) pitches.stream()
                .filter(pitch -> pitch.getScore() == Pitch.MAX_SCORE)
                .count();
        return getSize() == strikeNumber;
    }

    private boolean isLastStrike() {
        if (!isThirdNull()) {
            return pitches.get(2).getScore() == Pitch.MAX_SCORE;
        }
        return false;
    }

    private boolean isThirdNull() {
        return getSize() < MAX_SIZE;
    }

    private boolean isSpare() {
        if (isThirdNull()) {
            int firstTotalScore = pitches.get(0).getScore() + pitches.get(1).getScore();
            return firstTotalScore == Pitch.MAX_SCORE;
        }

        int secondTotalScore = pitches.get(1).getScore() + pitches.get(2).getScore();
        return secondTotalScore == Pitch.MAX_SCORE;
    }

    private boolean isMiss() {
        int totalScore = pitches.get(0).getScore() + pitches.get(1).getScore();
        return totalScore < Pitch.MAX_SCORE;
    }

    private boolean isMaxSize() {
        return getSize() == MAX_SIZE;
    }

    @Override
    public void validateScore(Pitch pitch) {
        int totalScore = pitches.get(0).getScore() + pitch.getScore();
        if (totalScore > Pitch.MAX_SCORE) {
            throw new IllegalArgumentException("프레임의 점수의 합이 10을 넘겼습니다.");
        }
    }

    @Override
    public State getState() {
        if (isStrike()) {
            return new Strike(pitches);
        }

        if (isSpare()) {
            return new Spare(pitches);
        }

        return new Miss(pitches);
    }

    @Override
    public int getSize() {
        return pitches.size();
    }
}
