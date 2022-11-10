package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Frame {
    FRAME_1_1(1, 1),
    FRAME_1_2(2, 1),
    FRAME_2_1(3, 2),
    FRAME_2_2(4, 2),
    FRAME_3_1(5, 3),
    FRAME_3_2(6, 3),
    FRAME_4_1(7, 4),
    FRAME_4_2(8, 4),
    FRAME_5_1(9, 5),
    FRAME_5_2(10, 5),
    FRAME_6_1(11, 6),
    FRAME_6_2(12, 6),
    FRAME_7_1(13, 7),
    FRAME_7_2(14, 7),
    FRAME_8_1(15, 8),
    FRAME_8_2(16, 8),
    FRAME_9_1(17, 9),
    FRAME_9_2(18, 9),
    FRAME_10_1(19, 10),
    FRAME_10_2(20, 10),
    FRAME_10_3(21, 10);

    private int order;
    private int number;

    Frame(int order, int number) {
        this.order = order;
        this.number = number;
    }

    public boolean isInitFrame() {
        return order % 2 != 0;
    }

    private Frame findByOrder(int order) {
        return Arrays.stream(Frame.values())
                     .filter(frame -> frame.order == order)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException());
    }

    public static List<Frame> frames() {
        return Arrays.stream(values()).filter(f -> f != FRAME_10_3).collect(Collectors.toList());
    }

    public Frame beforeFrame() {
        if (isInitFrame()) {
            return this;
        }
        return findByOrder(order - 1);
    }

    public int compareOrder(Frame frame) {
        return Integer.compare(this.order, frame.order);
    }

    public int number() {
        return number;
    }

    public boolean isBonusFrame() {
        return this == Frame.FRAME_10_3;
    }
}
