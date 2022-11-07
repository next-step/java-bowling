package bowling;

import bowling.domain.*;
import bowling.view.FramesResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        final Frames frames = Frames.init();
        final String participationName = InputView.inputParticipationName();
        final FramesResult framesResult = new FramesResult(new Name(participationName), frames);
        ResultView.resultTitlePrint(framesResult);

        while (frames.end()) {
            final Frame lastFrame = frames.lastFrame();
            getPitchNumberAndPrintResult(framesResult, lastFrame);
            frames.addFrame();
        }
    }

    private static void getPitchNumberAndPrintResult(final FramesResult framesResult, final Frame lastFrame) {

        while (lastFrame.canPitch()) {
            final int number = InputView.inputScore(lastFrame.getNumber());
            lastFrame.pitch(number);
            ResultView.resultTitlePrint(framesResult);
        }
    }
}
