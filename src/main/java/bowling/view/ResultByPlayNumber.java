package bowling.view;

import bowling.domain.FrameStrategy;

import java.util.Arrays;

interface Result {
    void print(FrameStrategy frame);
}

public enum ResultByPlayNumber implements Result {
    ZERO(0) {
        @Override
        public void print(FrameStrategy frame) {
            System.out.print(" " + SINGLE_FRAME);
        }
    },
    ONE(1) {
        @Override
        public void print(FrameStrategy frame) {
            printEachPitch(frame, 1);
            System.out.print(SINGLE_FRAME);
        }
    },
    TWO(2) {
        @Override
        public void print(FrameStrategy frame) {
            printEachPitch(frame, 1);
            System.out.print(BOUNDARY);
            printEachPitch(frame, 2);
            System.out.print(FULL_FRAME);
        }
    },
    THREE(3) {
        @Override
        public void print(FrameStrategy frame) {
            printEachPitch(frame, 1);
            System.out.print(BOUNDARY);
            printEachPitch(frame, 2);
            System.out.print(BOUNDARY);
            printEachPitch(frame, 3);
            System.out.println(FULL_FRAME);
        }
    };

    private static final String SINGLE_FRAME = "   |";
    private static final String BOUNDARY = "|";
    private static final String FULL_FRAME = " |";

    private final int playNumber;

    ResultByPlayNumber(int playNumber) {
        this.playNumber = playNumber;
    }

    private static void printEachPitch(FrameStrategy frame, int index) {
        String pitchResult = frame.result(index);
        System.out.print(pitchResult);
    }

    public static void printResult(FrameStrategy frame, int playNumber) {
        Arrays.stream(values())
                .filter(value -> value.playNumber == playNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .print(frame);
    }
}
