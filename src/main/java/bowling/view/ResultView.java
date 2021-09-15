package bowling.view;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String SEPARATOR = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final int MAX_BLANK_SIZE = 6;
    private static final char SPACE = ' ';
    private static final String BLANK = "";
    private static final int SCORES_LAST_INDEX = 1;
    private static final String GUTTER_NUMBER_STRING = "0";
    private static final String MAX_PIN_NUMBER_STRING = "10";


    public static void printScoreBoard(Player player) {
        printUpBoard();
        System.out.println();
        printBottomBoard(player);
    }

    private static void printBottomBoard(Player player) {
        System.out.print(SEPARATOR + alignText(player.getName()) + SEPARATOR);
        printScoreSectionBoard(player);
        printEmptySectionBoard(player.getFrameIndex());
        System.out.println();
    }

    private static void printEmptySectionBoard(int emptyFrameStartIndex) {
        for (int i = emptyFrameStartIndex; i < Frame.LAST_FRAME; i++) {
            System.out.print(alignText(BLANK) + SEPARATOR);
        }
    }

    private static void printScoreSectionBoard(Player player) {
        for (int i = 1; i < player.getFrameIndex(); i++) {
            printCurrentFrame(player, i);
        }
    }

    private static void printCurrentFrame(Player player, int i) {
        Frame currentFrame = player.getFrame(i);
        List<String> scores = currentFrame.scores();

        if (currentFrame.isSpare()) {
            scores.set(SCORES_LAST_INDEX, SPARE);
        }

        scores.replaceAll(score -> score.replaceAll(MAX_PIN_NUMBER_STRING, STRIKE).replaceAll(GUTTER_NUMBER_STRING, GUTTER));
        System.out.print(alignText(String.join(SEPARATOR, scores)) + SEPARATOR);
    }

    private static void printUpBoard() {
        System.out.print(SEPARATOR + alignText("NAME") + SEPARATOR);
        IntStream.rangeClosed(Frame.FIRST_FRAME, Frame.LAST_FRAME)
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
