package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ResultView {
    private static final String SCORE_TABLE_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BAR = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    public static void printFrames(PlayerName playerName, Frames frames) {
        System.out.println(SCORE_TABLE_HEAD);
        List<String> rowElements = new ArrayList<>();
        rowElements.add(playerName.getName());
        rowElements.addAll(triedFrames(frames));
        rowElements.addAll(remainingFrames(frames));

        String scoreTableRow = rowElements.stream()
                .map(ResultView::padded)
                .collect(joining(BAR, BAR, BAR));
        System.out.println(scoreTableRow);
    }

    private static List<String> remainingFrames(Frames frames) {
        int remainingFrameCount = Frames.MAX_FRAME_NUMBER - frames.lastFrameNumber();
        return IntStream.range(0, remainingFrameCount)
                .mapToObj(i -> "")
                .collect(toList());
    }

    private static List<String> triedFrames(Frames frames) {
        return frames.getFrames()
                .stream()
                .map(ResultView::frameString)
                .collect(toList());
    }

    private static String frameString(Frame frame) {
        return frame.getBowlings()
                .stream()
                .map(ResultView::bowlingString)
                .collect(joining(BAR));
    }

    private static String bowlingString(Bowling bowling) {
        if (bowling.isStrike()) {
            return STRIKE;
        }

        List<String> pinsList = bowling.getPinsList()
                .stream()
                .map(Pins::getFallenPins)
                .map(ResultView::pinsString)
                .collect(toList());

        if (bowling.isSpare()) {
            pinsList.set(bowling.tries() - 1, SPARE);
        }

        return String.join(BAR, pinsList);
    }

    private static String pinsString(int fallenPins) {
        if (fallenPins == 0) {
            return GUTTER;
        }

        return String.valueOf(fallenPins);
    }

    private static String padded(String string) {
        String result = " ".repeat(2) + string + " ".repeat(4);
        if (string.length() > 3) {
            result = " " + string + " ";
        }
        return result.substring(0, 6);
    }
}
