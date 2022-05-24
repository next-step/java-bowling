package bowling.domain;

public class PlayFrame {
    private final int number;
    private final String value;

    public PlayFrame(int number, String value) {
        this.number = number;
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public int number() {
        return this.number;
    }
}
