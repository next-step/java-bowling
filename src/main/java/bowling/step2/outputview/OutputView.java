package bowling.step2.outputview;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;
import bowling.step2.outputview.pitchresult.FrameResult;

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
        System.out.print("| " + sb + " |");
    }

    private static void showResult(Frame frame) {
        FrameResult frameResult = FrameResult.of(frame);

        String pitchResults = frameResult.getPitchResults();
        appendBlack(pitchResults);
    }

    private static void appendBlack(String name) {
        StringBuilder sb = new StringBuilder(name);
        while (sb.length() < 5) {
            sb.append(" ");
        }
        System.out.print(" " + sb + "|");
    }
}
