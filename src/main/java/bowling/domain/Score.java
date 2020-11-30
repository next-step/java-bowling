package bowling.domain;

import bowling.dto.ScoreDto;

class Score {
    private final int score;

    Score(int score) {
        this.score = score;
    }

    boolean isValid() {
        return score >= 0;
    }

    Score sum(Score score) {
        return new Score(this.score + score.score);
    }

    ScoreDto exportScoreDto() {
        return new ScoreDto(score);
    }
}
