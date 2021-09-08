package bowling.view;

import bowling.domain.Player;

import java.util.stream.IntStream;

public class ResultView {
    private static final String SEPARATOR = "|";
    private static final int MIN_FRAME_INDEX = 1;
    private static final int MAX_FRAME_INDEX = 10;
    private static final int MAX_BLANK_SIZE = 6;
    private static final char SPACE = ' ';
    private static final String BLANK = "";


    public static void printScoreBoard(Player player) {
        printUpBoard();
        printBottomBoard(player);
    }

    private static void printBottomBoard(Player player) {
        System.out.print(SEPARATOR + alignText(player.getName()) + SEPARATOR);
        printScoreSectionBoard(player);
    }

    private static void printScoreSectionBoard(Player player) {
        for (int i = 0; i < player.getFrameIndex(); i++) {
            alignText(String.join(SEPARATOR, player.getFrame(i).scores()));
        }
    }

    private static void printUpBoard() {
        System.out.print(SEPARATOR + alignText("NAME") + SEPARATOR);
        IntStream.rangeClosed(MIN_FRAME_INDEX, MAX_FRAME_INDEX)
                .mapToObj(i -> alignText(String.format("%02d", i)) + SEPARATOR)
                .forEach(System.out::print);
    }

    private static String alignText(String text) {
        StringBuilder sb = new StringBuilder();
        sb.setLength((MAX_BLANK_SIZE - text.length()) / 2);
        sb.append(text);
        sb.setLength(MAX_BLANK_SIZE);
        return sb.toString().replace('\0', SPACE);
    }
}
