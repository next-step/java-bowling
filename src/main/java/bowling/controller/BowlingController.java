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
        int pitchCount = 0;

        while(!isGameFinished) {
            boolean isFinished = false;
            NormalFrame normalFrame = NormalFrame.init();

            while(!isFinished) {
                int score = inputView.inputScore(frameIndex);
                Pitch pitch = Pitch.from(score);
                pitchCount++;
                normalFrame.add(pitch);
                resultView.printFrames(player.getName(), frameIndex);

                if(pitch.isStrike() || pitchCount >= 2) {
                    frameIndex++;
                    pitchCount = 0;
                    frames.add(normalFrame);
                    isFinished = true;
                }
            }

            if(frameIndex > 9) {
                isGameFinished = true;
            }
        }

        boolean lastGameFinished = false;
        while(!lastGameFinished) {
            boolean isFinished = false;
            FinalFrame finalFrame = FinalFrame.init();

            int lastScore = 0;
            while(!isFinished) {
                int score = inputView.inputScore(frameIndex);
                lastScore += score;

                Pitch pitch = Pitch.from(score);
                pitchCount++;
                finalFrame.add(pitch);
                resultView.printFrames(player.getName(), frameIndex);

                if(pitchCount == 2 && lastScore < 10) {
                    break;
                }

                if(pitchCount >= 3) {
                    frameIndex++;
                    pitchCount = 0;
                    isFinished = true;
                }
            }
            lastGameFinished = true;
        }
    }
}
