package bowling.domain;

public enum Type {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    NONE("");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
