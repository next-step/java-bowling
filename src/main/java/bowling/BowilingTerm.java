package bowling;

public enum BowilingTerm {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS("");

    private final String shape;

    BowilingTerm(String shape) {
        this.shape = shape;
    }

    public String shape() {
        return this.shape;
    }
}
