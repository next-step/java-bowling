package bowling.pitch;

import bowling.score.Score;

public class Pitch {

    private final Score score;

    private Pitch(Score score) {
        this.score = score;
    }

    public static Pitch initiate(Score score) {
        return new Pitch(score);
    }

    public int getScore() {
        return score.getScore();
    }
}
