package bowling_refactor.view;

public enum ScoreType {

    STRIKE(10, "X"),
    SPARE(10, "/"),
    MISS(-1, ""),
    GUTTER(0, "-");

    private int count;
    private String type;

    ScoreType(int count, String type) {
        this.count = count;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
