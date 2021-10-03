package bowling.presentation.output.constant;

public enum FrameSize {
    NORMAL(2), FINAL(3), INDENT(5);

    private final int value;

    FrameSize(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
