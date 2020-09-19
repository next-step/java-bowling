package bowling.view;

import bowling.domain.*;

public class PrintResult {

    private static final String SEPERATOR = " |  ";
    private static final String FRAME_SEPERATOR = "  |  ";
    private static final String NUMBER_SEPORATOR = "|";

    private PrintResult() {
    }

    public static void printFrames(Participants participants) {
        printScoreBoard();
        printResultScore(participants);
    }

    public static void printScoreBoard() {
        System.out.print(FRAME_SEPERATOR);
        System.out.print("NAME");
        for (int i = 0; i < BowlingFrame.TOTAL_FRAME; i++) {
            System.out.print(FRAME_SEPERATOR);
            System.out.print(zeroFill(i + 1));
        }
        System.out.println(FRAME_SEPERATOR);
    }

    private static void printResultScore(Participants participants) {
        System.out.print(FRAME_SEPERATOR);
        System.out.print(" " + participants.getName());
        System.out.print(FRAME_SEPERATOR);
        for (int i = 0; i < BowlingFrame.FRAME_COUNT; i++) {
            printFrame(participants.getBowlingFrame().getNormalFrames().get(i));
        }
        printFinalFrame(participants.getBowlingFrame().getFinalFrame());
        System.out.println();
    }


    private static void printFinalFrame(FinalFrame finalFrame) {
        System.out.print(finalFrame.getNormalFrame().getFirstScore().getScore());
        if (!finalFrame.getNormalFrame().getFirstScore().equals(Score.NONE) && !finalFrame.getNormalFrame().getFirstScore().equals(Score.STRIKE)) {
            System.out.print(NUMBER_SEPORATOR);
        }
        System.out.print(finalFrame.getNormalFrame().getSecondScore().getScore());
        if (!finalFrame.getFinalScore().equals(Score.NONE)) {
            System.out.print(NUMBER_SEPORATOR);
        }
        System.out.print(finalFrame.getFinalScore().getScore());
        System.out.print(getSeperator(finalFrame.getNormalFrame()));
    }


    public static String zeroFill(int number) {
        if (number < 10) {
            return "0" + number;
        }
        return " " + number + " ";
    }


    private static void printFrame(NormalFrame normalFrame) {
        System.out.print(normalFrame.getFirstScore().getScore());
        if (!normalFrame.getFirstScore().equals(Score.NONE) && !normalFrame.getFirstScore().equals(Score.STRIKE)) {
            System.out.print(NUMBER_SEPORATOR);
        }
        System.out.print(normalFrame.getSecondScore().getScore());
        System.out.print(getSeperator(normalFrame));
    }

    private static String getSeperator(NormalFrame normalFrame) {
        if (normalFrame.getFirstScore().equals(Score.NONE) && normalFrame.getSecondScore().equals(SecondScore.NONE)) {
            return FRAME_SEPERATOR;
        }
        return SEPERATOR;
    }
}
