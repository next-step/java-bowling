package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LastFrame implements Frame {

    private static final int STRIKE = 10;

    private final List<Score> scores = new ArrayList<>();

    public LastFrame() {

    }

    public LastFrame(int first, int second, int third) {
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
        this.scores.add(Score.of(third));
        validateSum();
    }

    @Override
    public void addScore(Score score) {
        if (isRemainChance()) {
            this.scores.add(score);
            validateSum();
            return;
        }
        throw new IllegalArgumentException("남은 기회가 없습니다.");
    }

    private void validateSum() {
        if (this.scores.size() < 2) {
            return;
        }
        if (this.scores.size() == 2) {
            validateSecondTimeScore();
            return;
        }
        if (this.scores.size() == 3) {
            validateThirdTimeScore();
            return;
        }
        throw new IllegalArgumentException();
    }

    private void validateSecondTimeScore() {
        if (first().isStrike()) {
            return;
        }
        if ((first().value() + second().value()) <= STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private void validateThirdTimeScore() {
        if (first().isStrike() && second().isStrike()) {
            return;
        }
        if ((first().value() + second().value()) == STRIKE) {
            return;
        }
        if (first().isStrike() && (second().value() + third().value()) <= STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < 2) {
            return true;
        }
        if (this.scores.size() < 3) {
            return validateRemainThirdTimeChance();
        }
        return false;
    }

    private boolean validateRemainThirdTimeChance() {
        if (first().isStrike() || second().isStrike() || ((first().value() + second().value()) == STRIKE)) {
            return true;
        }
        return false;
    }

    private Score first() {
        return this.scores.get(0);
    }

    private Score second() {
        return this.scores.get(1);
    }

    private Score third() {
        return this.scores.get(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(this.scores, lastFrame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
