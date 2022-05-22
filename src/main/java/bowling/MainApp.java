package bowling;

import bowling.controller.BowlingGameController;
import bowling.domain.Frames;
import bowling.ui.InputCui;
import bowling.ui.OutputCui;

public class MainApp {

    private static final int BASIC_FRAME_COUNT = 10;

    public static void main(String[] args) {
        InputCui inputCui = new InputCui();
        OutputCui outputCui = new OutputCui();
        BowlingGameController bowlingGame = new BowlingGameController();

        final String name = inputCui.inputPlayerNames();
        Frames frames = bowlingGame.createFrames(name);
        outputCui.drawFrames(frames);

        int hitCount;
        int remainedPins;
        for (int frameNumber = 1; frameNumber <= BASIC_FRAME_COUNT; ++frameNumber) {
            hitCount = inputCui.inputHitCount(frameNumber);

//            remainedPins = frames.play(frameNumber, hitCount);

            outputCui.drawFrames(frames);
        }

    }



}
