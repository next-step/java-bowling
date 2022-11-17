package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.BowlingFrames;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.pin.FallenPins;

public class ResultView {

    private static final int WIDTH = 6;

    private static String RESULT_DELIMITER = "|";

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
        System.out.printf("|%s|", getColumn(player.getName()));
        for (int i = 0; i < bowlingFrames.getSize(); i++) {
            String resultMarkers = getResultMarks(bowlingFrames.getFrame(i));

            System.out.printf("%s|", getColumn(resultMarkers));
        }
        System.out.println();
    }

    private static String getResultMarks(Frame frame) {
        FallenPins firstResult = frame.getFirstTurnResult();
        FallenPins secondResult = frame.getSecondTurnResult();

        String result = getResultMark(firstResult, null)
                + RESULT_DELIMITER
                + getResultMark(secondResult, firstResult);

        if (frame instanceof FinalFrame) {
            FinalFrame finalFrame = (FinalFrame) frame;
            result += (RESULT_DELIMITER + getResultMark(finalFrame.getBonusTurnResult(), null));
        }

        return result.replace(RESULT_DELIMITER + ResultMark.EMPTY.getMark(),
                ResultMark.EMPTY.getMark());
    }

    private static String getResultMark(FallenPins fallenPins, FallenPins previousFallenPins) {
        if (isEmpty(fallenPins)) {
            return ResultMark.EMPTY.getMark();
        }

        if (isGutter(fallenPins)) {
            return ResultMark.GUTTER.getMark();
        }

        if (isSpare(fallenPins, previousFallenPins)) {
            return ResultMark.SPARE.getMark();
        }

        if (isStrike(fallenPins, previousFallenPins)) {
            return ResultMark.STRIKE.getMark();
        }

        return String.valueOf(fallenPins.getCountOfPin());
    }

    private static boolean isStrike(FallenPins fallenPins, FallenPins previousFallenPins) {
        return isEmpty(previousFallenPins)
                && fallenPins.getCountOfPin() == FallenPins.MAX_COUNT_OF_PIN;
    }

    private static boolean isGutter(FallenPins fallenPins) {
        return fallenPins.getCountOfPin() == 0;
    }

    private static boolean isEmpty(FallenPins fallenPins) {
        return fallenPins == null;
    }

    private static boolean isSpare(FallenPins fallenPins, FallenPins previousFallenPins) {
        return previousFallenPins != null &&
                fallenPins.getCountOfPin() + previousFallenPins.getCountOfPin()
                        == FallenPins.MAX_COUNT_OF_PIN;
    }

    private static void printHeader(int frameSize) {
        System.out.print("| NAME |");
        for (int i = 1; i <= frameSize; i++) {
            System.out.printf("%s|", getColumn(String.format("%02d", i)));
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
