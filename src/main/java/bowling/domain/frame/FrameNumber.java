package bowling.domain.frame;

public enum FrameNumber {
    FRAME_1(1),
    FRAME_2(2),
    FRAME_3(3),
    FRAME_4(4),
    FRAME_5(5),
    FRAME_6(6),
    FRAME_7(7),
    FRAME_8(8),
    FRAME_9(9),
    FRAME_10(10);
    private int number;

    FrameNumber(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }
}
