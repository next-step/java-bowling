package bowling.controller;

import bowling.domain.FinalFrame;
import bowling.domain.FrameStrategy;
import bowling.domain.NormalFrame;
import bowling.domain.PinNumber;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingController {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        String name = inputView.name();

        List<FrameStrategy> normalFrames = new ArrayList<>();
        resultView.print(name, normalFrames);

        for (int thisFrame = FIRST_ROUND; thisFrame < LAST_ROUND; thisFrame++) {
            NormalFrame normalFrame = new NormalFrame();
            normalFrames.add(normalFrame);

            playNormalFrame(name, normalFrames, thisFrame, normalFrame);
            if (!normalFrame.hasSecond()) {
                continue;
            }
            playNormalFrame(name, normalFrames, thisFrame, normalFrame);
        }

        FinalFrame finalFrame = new FinalFrame();
        normalFrames.add(finalFrame);
        playFinalFrame(name, normalFrames, finalFrame);
        playFinalFrame(name, normalFrames, finalFrame);
        if (finalFrame.hasThird()) {
            playFinalFrame(name, normalFrames, finalFrame);
        }
    }

    private void playNormalFrame(String name, List<FrameStrategy> normalFrames, int thisFrame, FrameStrategy normalFrame) {
        normalFrame.play(new PinNumber(inputView.pinNumber(thisFrame)));
        resultView.print(name, normalFrames);
    }

    private void playFinalFrame(String name, List<FrameStrategy> normalFrames, FinalFrame finalFrame) {
        finalFrame.play(new PinNumber(inputView.pinNumber(LAST_ROUND)));
        resultView.print(name, normalFrames);
    }
}
