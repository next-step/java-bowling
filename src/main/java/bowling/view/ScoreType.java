package bowling.view;

public enum ScoreType {

    GUTTER("-"),
    SPARE("/"),
    STRIKE("X"),
    MISS("");

    private String type;

    ScoreType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}
