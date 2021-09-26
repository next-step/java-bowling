package bowling.presentation.output.constant;

public enum ScoreOutput {

    BOUNDARY("|"), SPACE(" "), STRIKE("X"), SPARE("/"), NO_POINT("-");

    private final String value;

    ScoreOutput(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
