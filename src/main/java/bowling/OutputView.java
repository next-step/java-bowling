package bowling;

import bowling.domain.BowlingLine;
import bowling.domain.Frame;

public class OutputView {

    public void printScore(BowlingLine bowlingLine) {
        printInitialFrame(bowlingLine);
        for (Frame frame : Frame.values()) {
            printFrame(frame, bowlingLine);
        }
    }

    private void printInitialFrame(BowlingLine bowlingLine) {
        printFrame();
    }

    public void printFrame(Frame frame, BowlingLine bowling) {
        if (bowling.availablePitching(frame)) {
            int pitching = bowling.pitching(frame);
            System.out.println(String.format("%s프레임 투구 : %s", frame.number(), pitching));
            printFrame();
            printScore(frame, bowling);
        }
    }

    private void printFrame() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void printScore(Frame currentFrame, BowlingLine bowlingLine) {
        System.out.print(String.format("|  %s |", bowlingLine.userName()));
        for (Frame frame : Frame.frames()) {
            printScore(currentFrame, frame, bowlingLine);
        }

        if (currentFrame.isBonusFrame()) {
            System.out.print(String.format("%s |", bowlingLine.score(currentFrame)));
        }
        System.out.println();
    }

    private void printScore(Frame currentFrame, Frame printFrame, BowlingLine bowlingLine) {
        if (currentFrame.compareOrder(printFrame) < 0) {
            String empty = printFrame.isInitFrame() ? "      |" : "";
            System.out.print(empty);
            return;
        }

        if (isSingleMark(currentFrame, printFrame, bowlingLine)) {
            System.out.print(String.format("  %s   |", bowlingLine.score(printFrame)));
            return;

        }

        if (printFrame.isInitFrame()) {
            System.out.print(String.format("  %s|", bowlingLine.score(printFrame)));
            return;
        }

        if(bowlingLine.isStrike(printFrame)) {
            return;
        }

        System.out.print(String.format("%s |", bowlingLine.score(printFrame)));
    }

    private boolean isSingleMark(Frame currentFrame, Frame printFrame, BowlingLine bowlingLine) {
        return printFrame.isInitFrame() && (printFrame == currentFrame || bowlingLine.isStrike(printFrame));
    }
}
