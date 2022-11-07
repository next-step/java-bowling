package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LastFrame implements Frame {

    private static final int STRIKE = 10;
    private static final int MAX_SCORE = 10;

    private final List<Score> scores = new ArrayList<>();

    public LastFrame() {

    }

    public LastFrame(int first, int second, int third) {
        validateSum(first, second);
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
        this.scores.add(Score.of(third));
    }

    private void validateSum(int first, int second) {
        if (first != STRIKE && (first + second) > MAX_SCORE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addScore(Score score) {
        if (isRemainChance()) {
            this.scores.add(score);
            return;
        }
        throw new IllegalArgumentException("남은 기회가 없습니다.");
    }

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < 2) {
            return true;
        }
        if (this.scores.size() < 3) {
            return thirdTimeValidate();
        }
        return false;
    }

    private boolean thirdTimeValidate() {
        Score first = this.scores.get(0);
        Score second = this.scores.get(1);
        if (first.isStrike() || second.isStrike() || ((first.value() + second.value()) == MAX_SCORE)) {
            return true;
        }
        return false;
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
