package bowling.domain;

public enum FrameStatus {

    STRIKE(1), SPARE(2), MISS(3), GUTTER(4), READY(5), END(6);

    private int code;

    FrameStatus(int code) {
        this.code = code;
    }

    int code() {
        return code;
    }
}
