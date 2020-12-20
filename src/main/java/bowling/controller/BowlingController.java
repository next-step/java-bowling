package bowling.controller;

import bowling.domain.NormalFrame;
import bowling.domain.NormalFrames;
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

        NormalFrames normalFrames = NormalFrames.init();
        boolean gameFinished = false;
        int frameIndex = 1;
        int pitchCount = 0;

        while(!gameFinished) {
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
                    normalFrames.add(normalFrame);
                    isFinished = true;
                }
            }

            if(frameIndex > 9) {
                gameFinished = true;
            }
        }

    }
}
