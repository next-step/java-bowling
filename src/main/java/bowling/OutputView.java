package bowling;

import bowling.domain.BowlingLine;
import bowling.domain.FrameNumber;

public class OutputView {

    public void printRecord(BowlingLine bowlingLine) {
        printInitialFrame(bowlingLine);
        for (FrameNumber number : FrameNumber.values()) {
            printFrame(number, bowlingLine);
        }
    }

    private void printInitialFrame(BowlingLine bowlingLine) {
        printFrame();
        printScore(bowlingLine);
    }

    public void printFrame(FrameNumber frame, BowlingLine bowling) {
        while (bowling.availablePitching(frame)) {
            int pitching = bowling.pitching(frame);
            System.out.println(String.format("%s프레임 투구 : %s", frame, pitching));
            printFrame();
            printScore(bowling);
        }
    }

    private void printFrame() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void printScore(BowlingLine bowlingLine) {
        System.out.print(String.format("|  %s |", bowlingLine.userName()));

        for (FrameNumber number : FrameNumber.values()) {
            String score = bowlingLine.score(number);
            System.out.print(String.format("  %s |", score));
        }
        System.out.println();
    }
}
