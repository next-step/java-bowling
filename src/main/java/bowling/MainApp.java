package bowling;

import bowling.controller.BowlingGameController;
import bowling.domain.Frames;
import bowling.ui.InputCui;
import bowling.ui.OutputCui;

public class MainApp {

    public static void main(String[] args) {
        InputCui inputCui = new InputCui();
        OutputCui outputCui = new OutputCui();
        BowlingGameController bowlingGame = new BowlingGameController();

        final String name = inputCui.inputPlayerNames();
        Frames frames = bowlingGame.createFrames(name);
        outputCui.drawFrames(frames);

        play(inputCui, outputCui, frames);
    }

    private static void play(InputCui inputCui, OutputCui outputCui, Frames frames) {
        int hitCount;
        int frameNumber = 1;
        while (!frames.isEndGame()) {
            hitCount = inputCui.inputHitCount(frameNumber);

            frameNumber += frames.throwBall(hitCount);

            outputCui.drawFrames(frames);
        }
    }

    private static void shot(Frames frames, int hitCount, int frameNumber) {
        if (frameNumber == 10) {

        }

        frames.throwBall(hitCount);
    }

}
