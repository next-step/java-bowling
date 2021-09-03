package bowling.step2.outputView;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;
import bowling.step2.outputView.pitchResult.FrameResult;

import java.util.List;

public class OutputView {
    public static void showResult(Lane lane) {
        showHeader();
        showName(lane.participant()
                .name());

        List<Frame> current = lane.current();

        current.forEach(OutputView::showResult);

        System.out.println();
    }

    private static void showHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void showName(String name) {
        StringBuilder sb = new StringBuilder(name);
        while (sb.length() < 4) {
            sb.append(" ");
        }
        System.out.print("| " + sb.toString() + " |");
    }

    private static void showResult(Frame frame) {
//        if (frame instanceof LastFrame) {
//            showLastFrame(frame);
//            return;
//        }
//        showNormalFrame(frame);

        int size = frame.current()
                .size();

        FrameResult frameResult = FrameResult.of(frame);
    }

    private static void showNormalFrame(Frame frame) {
        List<Integer> current = frame.current();
        for (int i = 0; i < current.size(); i++) {
            if (i == 0) {
                if (current.get(i) == 10) {
                    System.out.print("  X  ");
                    break;
                } else {
                    System.out.print(" " + current.get(i) + "|");
                }
            } else {
                if (current.get(i) == 10) {
                    System.out.print("| ");
                } else {
                    System.out.print(current.get(i) + " ");
                }
            }
        }

        System.out.print(" |");
    }

    private static void showLastFrame(Frame frame) {
        List<Integer> current = frame.current();
        for (int i = 0; i < current.size(); i++) {
            if (i == 0) {
                if (current.get(i) == 10) {
                    System.out.print("X|");
                } else {
                    System.out.print(" " + current.get(i) + "|");
                }
            } else if (i == 1) {
                if (current.get(i) == 10) {
                    System.out.print("X|");
                } else if (current.get(i) + current.get(0) == 10) {
                    System.out.print("/|");
                } else {
                    System.out.print(current.get(i) + " ");
                }
            } else {
                if (current.get(i) == 10) {
                    System.out.print("X");
                } else if (current.get(i) + current.get(1) == 10) {
                    System.out.print("/");
                } else {
                    System.out.print(current.get(i));
                }
            }
        }

        System.out.print("|");
    }
}
