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
            printState(frame, 1);
            System.out.print(SINGLE_FRAME);
        }
    },
    TWO(2) {
        @Override
        public void print(FrameStrategy frame) {
            printState(frame, 1);
            System.out.print(BOUNDARY);
            printState(frame, 2);
            System.out.print(FULL_FRAME);
        }
    },
    THREE(3) {
        @Override
        public void print(FrameStrategy frame) {
            printState(frame, 1);
            System.out.print(BOUNDARY);
            printState(frame, 2);
            System.out.print(BOUNDARY);
            printState(frame, 3);
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

    private static void printState(FrameStrategy frame, int index) {
        String frameState = frame.result(index);
        System.out.print(frameState);
    }

    public static void resultString(int playNumber, FrameStrategy frame) {
        Arrays.stream(values())
                .filter(value -> value.playNumber == playNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .print(frame);
    }
}
