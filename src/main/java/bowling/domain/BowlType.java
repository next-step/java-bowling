package bowling.domain;

public enum BowlType {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FORE("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    NONE("");

    private final String type;

    BowlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
