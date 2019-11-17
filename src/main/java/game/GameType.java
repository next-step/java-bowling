package game;

import static score.Score.MAX_SCORE;

public enum GameType {
    STRIKE, SPARE, MISS,
    ;

    public static GameType get(int size, int score) {
        if (size == 1 && score == MAX_SCORE) {
            return STRIKE;
        }
        if (score == MAX_SCORE) {
            return SPARE;
        }
        return MISS;
    }
}
