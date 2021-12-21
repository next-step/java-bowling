package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.GameInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String NAME = "NAME";
    private static final String BOARD_HEADER_CONTENT_FORMAT = "|  %02d  ";
    private static final String BOARD_DIVISION = "|";
    private static final String PITCH_INFO = "%d프레임 투구 : %d\n";
    private static final String BOARD_FRAME_BODY_CONTENT_FORMAT = "| %4s ";
    private static final String WHITE_SPACE = " ";

    public static void showBoard(GameInfo gameInfo, Frame frame) {
        printPitchInfo(gameInfo.noOf(frame), gameInfo.fallDownPinsCountOf(frame));

        printFrame(gameInfo.player().name(), gameInfo.frameResults(), gameInfo.scoreResults());
    }

    public static void printFrame(String name, List<String> frameResults, List<String> frameScores) {
        printFrameHeader();

        printFrameBody(name, frameResults);
        printFrameBody(WHITE_SPACE, frameScores);
        newLine();
    }

    private static void printPitchInfo(int no, int fallDownPinCount) {
        System.out.printf(PITCH_INFO, no + 1, fallDownPinCount);
    }

    private static void printFrameHeader() {
        printPlayerName(NAME);
        IntStream.rangeClosed(1, 10)
                .forEach(no -> System.out.printf(BOARD_HEADER_CONTENT_FORMAT, no));
        headEnd();
    }

    private static void printFrameBody(String name, List<String> results) {
        printPlayerName(name);
        printFrameBodyContent(results);
        printFrameBodyContent(new ArrayList<>(
                Collections.nCopies(10 - results.size(), WHITE_SPACE)));
        bodyEnd();
    }

    private static void printFrameBodyContent(List<String> frameResults) {
        frameResults.forEach(result ->
                System.out.printf(BOARD_FRAME_BODY_CONTENT_FORMAT, checkLengthAndFillBlank(result)));
    }

    private static void printPlayerName(String name) {
        System.out.printf(BOARD_FRAME_BODY_CONTENT_FORMAT, name);
    }

    private static String checkLengthAndFillBlank(String result) {
        int length = result.length();
        if (length == 0) {
            return result + WHITE_SPACE + WHITE_SPACE + WHITE_SPACE;
        }
        if (length == 1) {
            return result + WHITE_SPACE + WHITE_SPACE;
        }
        return result;
    }

    private static void headEnd() {
        System.out.print(BOARD_DIVISION);
        newLine();
    }

    private static void bodyEnd() {
        System.out.print(BOARD_DIVISION);
        newLine();
    }

    private static void newLine() {
        System.out.println();
    }
}
