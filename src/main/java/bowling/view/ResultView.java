package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.BowlingFrames;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.pin.FallenPins;

public class ResultView {

    private static final int WIDTH = 6;

    private static final String RESULT_DELIMITER = "|";

    private ResultView() {

    }

    public static void printFrame(Player player) {
        printHeader(player.getFrames().getSize());
        printRow(player.getFrames(), player);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
        System.out.println("입력값을 확인하고 다시 입력해주세요.");
    }

    private static void printRow(BowlingFrames bowlingFrames, Player player) {
        System.out.printf("%s%s%s", RESULT_DELIMITER, getColumn(player.getName()),
                RESULT_DELIMITER);
        for (int i = 0; i < bowlingFrames.getSize(); i++) {
            String resultMarkers = getResultMarks(bowlingFrames.getFrame(i));

            System.out.printf("%s%s", getColumn(resultMarkers), RESULT_DELIMITER);
        }
        System.out.println();
    }

    private static String getResultMarks(Frame frame) {
        FallenPins firstResult = frame.getFirstTurnResult();
        FallenPins secondResult = frame.getSecondTurnResult();

        String result = MarkConverter.getResultMark(firstResult, null)
                + RESULT_DELIMITER
                + MarkConverter.getSecondMark(firstResult, secondResult);

        if (frame instanceof FinalFrame) {
            FinalFrame finalFrame = (FinalFrame) frame;
            result += (RESULT_DELIMITER + MarkConverter.getResultMark(finalFrame.getBonusTurnResult(), null));
        }

        return result.replace(RESULT_DELIMITER + ResultMark.EMPTY.getMark(),
                ResultMark.EMPTY.getMark());
    }

    private static void printHeader(int frameSize) {
        System.out.printf("%s NAME %s", RESULT_DELIMITER, RESULT_DELIMITER);
        for (int i = 1; i <= frameSize; i++) {
            System.out.printf("%s%s", getColumn(String.format("%02d", i)), RESULT_DELIMITER);
        }
        System.out.println();
    }

    private static String getColumn(String data) {
        int blankSize = WIDTH - data.length();
        String leftBlank = " ".repeat(blankSize / 2 + (blankSize % 2));
        String rightBlank = " ".repeat(blankSize / 2);
        return leftBlank + data + rightBlank;
    }

}
