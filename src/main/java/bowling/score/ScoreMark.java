package bowling.score;

public enum ScoreMark {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private String mark;

    ScoreMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return mark;
    }

}
