package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pitch;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void start() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Player player = inputView.inputPlayer();
        resultView.printFirstFrame(player.getName());

        Frames frames = Frames.init();
        boolean gameFinished = false;
        int frameIndex = 1;
        int pitchCount = 0;

        while(!gameFinished) {
            boolean isFinished = false;
            Frame frame = Frame.init();

            while(!isFinished) {
                int score = inputView.inputScore(frameIndex);
                Pitch pitch = Pitch.from(score);
                pitchCount++;
                frame.add(pitch);
                resultView.printFrames(player.getName(), frameIndex);

                if(pitch.isStrike() || pitchCount >= 2) {
                    frameIndex++;
                    pitchCount = 0;
                    frames.add(frame);
                    isFinished = true;
                }
            }

            if(frameIndex > 9) {
                gameFinished = true;
            }
        }

    }
}
