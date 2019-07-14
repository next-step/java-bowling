package View;

import java.util.stream.IntStream;

import static domain.Bowling.TOTAL_FRAME_COUNT;

public class OutView {
    private static String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private static String ASK_FRAME_SCORE = "%d프레임 투구 : ";
    private static String NAME = "NAME";
    private static String LINE_CONNECTOR = " | ";

    public static void printBlankLine() {
        System.out.println();
    }

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        print(s + "\n");
    }

    public static void askPlayerName() {
        print(ASK_PLAYER_NAME);
    }

    public static void askFrameScore(int frameNumber) {
        System.out.printf(ASK_FRAME_SCORE, frameNumber);
    }

    public static void showFrameHeader() {
        StringBuilder builder = new StringBuilder("| " + NAME + LINE_CONNECTOR);
        IntStream.rangeClosed(1, TOTAL_FRAME_COUNT)
                .peek(number -> builder.append(String.format(" %02d ", number)))
                .forEach(n -> builder.append(LINE_CONNECTOR));
        println(builder.toString());
    }

    public static void showFrameResult(String playerName, String result) {
        StringBuilder builder = new StringBuilder("| " + String.format("%4s", playerName) + LINE_CONNECTOR);
        builder.append(result);
        println(builder.toString());
    }
}
