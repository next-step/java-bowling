package bowling.view;

import bowling.domain.*;

public class ResultView {

    public static void printRound(Name name, Round round) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("| %s  |", name.getName());

        round.forEach(ResultView::printFrame);
        System.out.println();
    }

    private static void printFrame(Frame frame) {
        String frameString = "  ";
        FrameStatus firstStatus = frame.getFirstStatus();
        frameString += getFrameCharacter(firstStatus, frame.getFirstHit());
        FrameStatus secondStatus = frame.getSecondStatus();
        frameString += getSecondFrameCharacter(secondStatus, frame.getSecondHit());
        System.out.print(frameString);
    }

    private static String getFrameCharacter(FrameStatus frameStatus, Hit hit) {
        if (frameStatus == FrameStatus.BEFORE) {
            return " ";
        }
        if (frameStatus == FrameStatus.STRIKE) {
            return "X";
        }
        if (frameStatus == FrameStatus.GUTTER) {
            return "-";
        }
        return Integer.toString(hit.getScore());
    }

    private static String getSecondFrameCharacter(FrameStatus frameStatus, Hit hit) {
        if (frameStatus == FrameStatus.BEFORE
                || frameStatus == FrameStatus.SKIP) {
            return "   |";
        }
        if (frameStatus == FrameStatus.GUTTER) {
            return "|- |";
        }
        if (frameStatus == FrameStatus.SPARE) {
            return "|/ |";
        }
        return String.format("|%d |", hit.getScore());
    }
}
