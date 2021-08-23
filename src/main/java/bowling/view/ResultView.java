package bowling.view;

import java.util.stream.IntStream;

public class ResultView {
    private static final String NAME = "| NAME |";
    private static final String FRAME = "  %02d  |";
    private static final int FIRST_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;

    // 프레임 헤드
    public static void printHeader() {
        print(NAME);
        IntStream.rangeClosed(FIRST_FRAME_NUMBER, LAST_FRAME_NUMBER)
            .forEach(frameNumber -> {
                print(String.format(FRAME, frameNumber));
            });
    }

    // TODO: 결과마다 출력해야됨




    private static void print(String message) {
        System.out.print(message);
    }
}
