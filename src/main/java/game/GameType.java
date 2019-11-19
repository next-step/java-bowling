package game;

import java.util.Arrays;

import static score.ScoreType.MAX_SCORE;

public enum GameType {
    STRIKE(1, MAX_SCORE), SPARE(2, MAX_SCORE), MISS(2),
    ;
    private int size;
    private int score;

    GameType(int size) {
        this.size = size;
    }

    GameType(int size, int score) {
        this.size = size;
        this.score = score;
    }

    public static GameType get(int size, int score) {
        return Arrays.stream(values()).filter(gameType -> gameType.size == size)
                .filter(gameType -> gameType.score == score)
                .findFirst().orElse(MISS);
    }
}
