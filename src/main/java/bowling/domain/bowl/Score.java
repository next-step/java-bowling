package bowling.domain.bowl;

import bowling.dto.ScoreDto;
import bowling.exception.BadScoreException;

class Score {
    private final int score;

    Score(int score) {
        if (score < 0 || score > 300) {
            throw new BadScoreException("score 는 0 이상, 300 이하여야 합니다.");
        }
        this.score = score;
    }

    ScoreDto exportScoreDto() {
        return new ScoreDto(score);
    }
}
