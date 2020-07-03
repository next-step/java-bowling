package bowling.domain.score;

import java.util.Arrays;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public enum Result {

    STRIKE {
        @Override
        public boolean isMatch(Score first, Score second) {
            return first.equals(MAX_SCORE) && second.equals(MIN_SCORE);
        }

        @Override
        public Score calculateTotalScore(Score score, FrameScore nextFrameScore) {
            return score.add(nextFrameScore.calculateTotalScore());
        }
    },

    SPARE {
        @Override
        boolean isMatch(Score first, Score second) {
            return !first.equals(MAX_SCORE) && first.add(second).equals(MAX_SCORE);
        }

        @Override
        public Score calculateTotalScore(Score score, FrameScore nextFrameScore) {
            return nextFrameScore.getFirst()
                    .map(s -> s.add(score))
                    .orElse(score);
        }
    },

    MISS {
        @Override
        boolean isMatch(Score first, Score second) {
            return first.add(second).isBetween(MIN_SCORE, MAX_SCORE);
        }

        @Override
        public Score calculateTotalScore(Score score, FrameScore nextFrameScore) {
            return score;
        }
    },

    GUTTER {
        @Override
        boolean isMatch(Score first, Score second) {
            return first.equals(MIN_SCORE) && second.equals(MIN_SCORE);
        }

        @Override
        public Score calculateTotalScore(Score score, FrameScore nextFrameScore) {
            return score;
        }
    };

    public static Result findByFrameScore(Score first, Score second) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(first, second))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("결과 확인이 불가능한 점수입니다 : %d,%d", first.getContent(), second.getContent()))
                );
    }

    abstract boolean isMatch(Score first, Score second);
    abstract public Score calculateTotalScore(Score score, FrameScore nextFrameScore);
}
