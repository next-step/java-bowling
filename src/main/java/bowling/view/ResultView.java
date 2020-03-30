package bowling.view;

import bowling.application.Response;
import bowling.domain.frame.Frame;
import bowling.domain.state.Bonus;

import java.util.List;

public class ResultView {

    public static void view(Response response) {
        BowlingView.getRoundBoard();
        List<Frame> frames = response.getFrames();
        getFrameBoard(frames, response.getName());
        getScore(frames);
        System.out.println();
        System.out.println();
    }

    private static void getFrameBoard(List<Frame> frames, String name) {
        System.out.print(String.format("|  %s |", name));
        for (Frame frame : frames) {
            getFrame(frame);
        }
        BowlingView.getCollectFrame(frames.size());
    }

    private static void getScore(List<Frame> frames) {
        System.out.print("| SCORE|");
        int sum = 0;
        for (Frame frame : frames) {
            if (frame.isFinish()) {
                if (frame.isCalculation()) {
                    sum += frame.getScore();
                    BowlingView.getScoreFrame(sum);
                } else {
                    BowlingView.getScoreFrameByEmpty();
                }
            } else {
                BowlingView.getScoreFrameByEmpty();
            }
        }
        BowlingView.getCollectFrame(frames.size());
    }

    private static void getFrame(Frame frame) {
        if (frame.isFinish()) {
            getBonusFrame(frame);
            return;
        }
        getReadyFrame(frame);
    }

    private static void getReadyFrame(Frame frame) {
        if (frame.getFrameNumber() == 10) {
            BowlingView.getFinalFinishFrame(frame.display());
            return;
        }
        BowlingView.getReadyFrame(frame.display());
    }

    private static void getBonusFrame(Frame frame) {
        if (frame.getState() instanceof Bonus) {
            BowlingView.getFinalFinishFrame(frame.display());
            return;
        }
        BowlingView.getFinishFrame(frame.display());
    }

}
