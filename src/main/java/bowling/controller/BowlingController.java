package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void start() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Player player = inputView.inputPlayer();
        resultView.printFirstFrame(player.getName());

        Frames frames = Frames.init();
        boolean isGameFinished = false;
        int frameIndex = 1;

        while(!isGameFinished) {
            Frame normalFrame = NormalFrame.init();

            while(!normalFrame.isFinish()) {
                int score = inputView.inputScore(frameIndex);
                Pitch pitch = Pitch.from(score);
                normalFrame.add(pitch);
                resultView.printFrames(player.getName(), frameIndex);
            }
            frameIndex++;
            frames.add(normalFrame);

            if(frameIndex > 9) {
                isGameFinished = true;
            }
        }

        boolean lastGameFinished = false;
        while(!lastGameFinished) {
            Frame finalFrame = FinalFrame.init();

            while(!finalFrame.isFinish()) {
                int score = inputView.inputScore(frameIndex);

                Pitch pitch = Pitch.from(score);
                finalFrame.add(pitch);
                resultView.printFrames(player.getName(), frameIndex);
            }
            frameIndex++;
            frames.add(finalFrame);
            lastGameFinished = true;
        }
    }
}
