package game;

public enum GameType {
    STRIKE, SPARE, MISS;

    public static GameType get(int size, int score) {
        if (size > 3) {
            throw new IllegalArgumentException("한 프레임에 세번 이상 공을 던질 수 없습니다.");
        }
        if (size == 1 && score == 10) {
            return STRIKE;
        }
        if (score == 10) {
            return SPARE;
        }
        return MISS;
    }
}
