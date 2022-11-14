package bowling;

import bowling.domain.*;
import bowling.view.FramesResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        final Frames frames = Frames.init();
        final String participationName = InputView.inputParticipationName();
        final FramesResult framesResult = new FramesResult(Participant.from(participationName), frames);
        ResultView.resultPrint(framesResult);

        while (!frames.isLast()) {
            inputBowlNumberAndPrintResult(framesResult, frames);
        }
    }

    private static void inputBowlNumberAndPrintResult(final FramesResult framesResult, final Frames frames) {

        Frame lastFrame = frames.lastFrame();

        while (lastFrame.canBowl()) {
            int number = InputView.inputScore(lastFrame.getFrameNumber());
            lastFrame.bowl(number);
            ResultView.resultPrint(framesResult);
        }

        frames.addFrame();
    }
}
