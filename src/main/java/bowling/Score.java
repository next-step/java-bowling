package bowling;

public enum Score {
    STRIKE(10, "x"),
    SPARE(10, "/"),
    MISS_1(1, "1"),
    MISS_2(2, "2"),
    MISS_3(3, "3"),
    MISS_4(4, "4"),
    MISS_5(5, "5"),
    MISS_6(6, "6"),
    MISS_7(7, "7"),
    MISS_8(8, "8"),
    MISS_9(9, "9"),
    GUTTER(0, "-");

    private int score;
    private String display;

    Score(int score, String display) {
        this.score = score;
        this.display = display;
    }

    public static Score of(int number) {
        for (Score score : values()) {
            if (score.getScore() == number) {
                return score;
            }
        }
        throw new IllegalArgumentException("최대 투구 점수는 10점입니다.");
    }

    public static Score of(int before, int current) {
        if (before + current == 10) {
            return Score.SPARE;
        }
        return of(current);
    }

    public int getScore() {
        return this.score;
    }

    public String getDisplay() {
        return this.display;
    }
}
