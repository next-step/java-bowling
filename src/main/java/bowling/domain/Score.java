package bowling.domain;

import bowling.dto.ScoreDto;
import bowling.exception.BadScoreException;

class Score {
    private final int score;

    Score(int score) {
        if (!isValid(score)) {
            throw new BadScoreException("score 는 0 이상, 300 이하여야 합니다.");
        }
        this.score = score;
    }

    static boolean isValid(int score) {
        return score >= 0 && score <= 300;
    }

    Score sum(Score score) {
        return new Score(this.score + score.score);
    }

    ScoreDto exportScoreDto() {
        return new ScoreDto(score);
    }
}
