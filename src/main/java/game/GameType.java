package game;

public enum GameType {
    STRIKE, SPARE, MISS,
    ;

    public static GameType get(int size, int score) {
        if (size == 1 && score == 10) {
            return STRIKE;
        }
        if (score == 10) {
            return SPARE;
        }
        return MISS;
    }
}
