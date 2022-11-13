package bowling;

import java.util.List;

public class ResultView {

    public static void printResult(UserName name, Frames frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
    }

    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(UserName name) {
        System.out.print("|");
        System.out.printf("%5s ",name.getName());
    }

    private static void printFrames(Frames frames) {
        for (Frame frame : frames.getValues()) {
            if (frame.isFinalFrame()) {
                System.out.printf("| %-5s", printFinalFrame(frame));
                System.out.print("|");
                System.out.println();
                return;
            }
            System.out.printf("| %-5s", printNormalFrame(frame));
        }

        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - frames.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }

    private static String printNormalFrame(Frame frame) {
        Score score = frame.getScores();
        List<Pin> scores = score.getTotalScore();
        StringBuilder output = new StringBuilder();

        if (frame.isFinished()) {
            if (scores.size() == Score.FIRST_ROUND) {
                return printPinStatus(Score.FIRST_ROUND, scores.get(0));
            }
            output.append(printPinStatus(scores.size(), scores.get(0)));
            output.append("|");
            output.append(printPinStatus(scores.size(), scores.get(1)));
            return output.toString();
        }
        return printPinStatus(scores.size(), scores.get(0));
    }

    public static String printFinalFrame(Frame frame) {
        Score score = frame.getScores();
        List<Pin> scores = score.getTotalScore();
        StringBuilder output = new StringBuilder();

        if (scores.size() == Score.BONUS_ROUND) {
            output.append(printPinStatus(scores.size(), scores.get(0)));
            output.append("|");
            output.append(printFinalPinStatus(score, scores.get(1)));
            output.append("|");
            output.append(printPinStatus(scores.size(), scores.get(2)));
            return output.toString();
        }

        if (scores.size() == Score.SECOND_ROUND) {
            output.append(printPinStatus(Score.FIRST_ROUND, scores.get(0)));
            output.append("|");
            output.append(printFinalPinStatus(score, scores.get(1)));
            return output.toString();
        }

        return printPinStatus(scores.size(), scores.get(0));
    }

    private static String printPinStatus(int scoreSize, Pin pin) {
        if (scoreSize == Score.FIRST_ROUND && pin.isMax()) {
            return "X";
        }

        if (scoreSize == Score.SECOND_ROUND && pin.isMax()) {
            return "/";
        }

        if (scoreSize == Score.BONUS_ROUND && pin.isMax()) {
            return "X";
        }

        if (pin.isGutter()) {
            return "-";
        }

        if (pin.isMiss()) {
            return String.valueOf(pin.getFalledPins());
        }

        return String.valueOf(pin.getFalledPins());
    }

    private static String printFinalPinStatus(Score score, Pin pin) {
        if (score.isStrike() && pin.isMax()) {
            return "X";
        }

        if (score.isSpare()) {
            return "/";
        }

        return String.valueOf(pin.getFalledPins());
    }
}
