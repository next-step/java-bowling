package bowling.domain;

public enum Indication {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    SEPARATOR("|")
    ;

    private String indication;

    Indication(String indication) {
        this.indication = indication;
    }

    @Override
    public String toString() {
        return indication;
    }
}