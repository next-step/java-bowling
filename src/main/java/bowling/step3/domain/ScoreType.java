package bowling.step3.domain;

public enum ScoreType {
    STRIKE("X"),
    SPARED("/"),
    GUTTER("-");

    private final String value;

    ScoreType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
