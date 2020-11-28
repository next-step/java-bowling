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

    ScoreDto exportScoreDto() {
        return new ScoreDto(score);
    }
}
