package bowling.domain;

class Score {
    private final int score;

    Score(int score) {
        this.score = score;
    }

    boolean isValid() {
        return score >= 0;
    }
}
