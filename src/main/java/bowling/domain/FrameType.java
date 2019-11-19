package bowling.domain;

public enum FrameType {

    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private String code;

    FrameType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
