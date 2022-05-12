package bowling.ui;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.game.Bowling;

import java.util.List;

import static bowling.domain.frame.NormalFrameNo.MAX_NORMAL_FRAME_NO;
import static bowling.domain.pin.PinNo.MAX_PIN_NO;
import static bowling.domain.pin.PinNo.MIN_PIN_NO;

public class OutputView {

    private static final String NAME_FORMAT = "| %s  ";
    private static final String FRAME_FORMAT = "|  %s ";

    private static final String NORMAL_FRAME_STRIKE = "X  ";
    private static final String FINAL_FRAME_STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String EMPTY_FRAME = "|      ";

    private OutputView() {
    }

    public static void printBowling(Bowling bowling) {
        System.out.println();
        printHeader();
        printName(bowling.getPlayerName());
        printFrames(bowling);
        System.out.println();
    }

    private static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10");
    }

    private static void printName(String name) {
        System.out.printf(NAME_FORMAT, name);
    }

    private static void printFrames(Bowling bowling) {
        printNormalFrames(bowling.getNormalFrames());
        printFinalFrame(bowling);
        System.out.println();
    }

    private static void printNormalFrames(List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            System.out.printf(FRAME_FORMAT, buildNormalFrameExpression(frames.get(i)));
        }
        for (int i = 0; i < MAX_NORMAL_FRAME_NO - frames.size(); i++) {
            printEmptyFrame();
        }
    }

    private static void printFinalFrame(Bowling bowling) {
        bowling.getFinalFrame()
                .ifPresentOrElse(
                        frame -> System.out.printf(FRAME_FORMAT, buildFinalFrameExpression(frame)),
                        OutputView::printEmptyFrame);
    }

    private static void printEmptyFrame() {
        System.out.print(EMPTY_FRAME);
    }

    private static String buildNormalFrameExpression(Frame frame) {
        if (frame.isStrike()) {
            return NORMAL_FRAME_STRIKE;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(toFrameExpression(frame.getFirstNo()));
        sb.append("|");

        if (frame.isSpare()) {
            sb.append(SPARE);
            return sb.toString();
        }
        sb.append(toFrameExpression(frame.getSecondNo()));
        return sb.toString();
    }

    private static String buildFinalFrameExpression(Frame frame) {
        StringBuilder sb = new StringBuilder();
        sb.append(toFrameExpression(frame.getFirstNo()));
        sb.append("|");

        sb.append(transformSecondNumber(frame));

        if (frame.isStrike() || frame.isSpare()) {
            sb.append("|");
            ((FinalFrame) frame).getExtraNo()
                    .ifPresentOrElse(
                        extraNo -> sb.append(toFrameExpression(extraNo)),
                        () -> {
                            throw new IllegalStateException("missing extra number");
                        });
        }

        return sb.toString();
    }

    private static String toFrameExpression(int no) {
        if (no == MIN_PIN_NO) {
            return GUTTER;
        }
        if (no == MAX_PIN_NO) {
            return FINAL_FRAME_STRIKE;
        }
        return String.valueOf(no);
    }

    private static String transformSecondNumber(Frame frame) {
        if (frame.isSpare()) {
            return SPARE;
        }
        return toFrameExpression(frame.getSecondNo());
    }
}
