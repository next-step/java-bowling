package bowling.domain;

import java.util.List;
import java.util.Objects;

public interface FrameScore {

    FrameScore unknown = new UnknownScore();

    void addScore(int additionScore);

    boolean hasRemainingAddition();

    int getIntValue();

    static FrameScore of(int additionCount, List<Integer> countListOfFallDownPins) {
        return new MutableFrameScore(
                AdditionCounter.of(additionCount),
                countListOfFallDownPins.stream()
                        .reduce(Integer::sum)
                        .orElse(0));
    }

    static FrameScore of(AdditionCounter additionCounter, int currentScore){
        return new MutableFrameScore(additionCounter, currentScore);
    }

    static FrameScore of(int score){
        return new ImmutableScore(score);
    }

    class MutableFrameScore implements FrameScore {

        private final AdditionCounter additionCounter;
        private int score;

        public MutableFrameScore(AdditionCounter additionCounter, int baseScore) {
            this.additionCounter = additionCounter;
            this.score = baseScore;
        }

        @Override
        public void addScore(int additionScore) {
            if (hasRemainingAddition()) {
                score += additionScore;
                additionCounter.count();
            }
        }

        @Override
        public boolean hasRemainingAddition() {
            return !additionCounter.isDone();
        }

        @Override
        public int getIntValue() {
            if (hasRemainingAddition()) {
                throw new IllegalStateException("프레임 점수를 계산할 수 없는 상태 입니다");
            }
            return score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MutableFrameScore that = (MutableFrameScore) o;
            return score == that.score && Objects.equals(additionCounter, that.additionCounter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(additionCounter, score);
        }
    }

    class ImmutableScore implements FrameScore {

        private final int score;

        public ImmutableScore(int score) {
            this.score = score;
        }

        @Override
        public void addScore(int additionScore) {
            throw new IllegalStateException("점수를 수정할 수 없습니다");
        }

        @Override
        public boolean hasRemainingAddition() {
            return false;
        }

        @Override
        public int getIntValue() {
            return score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ImmutableScore that = (ImmutableScore) o;
            return score == that.score;
        }

        @Override
        public int hashCode() {
            return Objects.hash(score);
        }
    }

    class UnknownScore extends ImmutableScore {
        private UnknownScore(){
            this(0);
        }
        private UnknownScore(int score) {
            super(score);
        }
    }
}
