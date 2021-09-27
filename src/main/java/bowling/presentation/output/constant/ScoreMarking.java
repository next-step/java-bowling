package bowling.presentation.output.constant;

public enum ScoreMarking {

    BOUNDARY("|"), SPACE(" "), STRIKE("X"), SPARE("/"), NO_POINT("-");

    private final String value;

    ScoreMarking(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
