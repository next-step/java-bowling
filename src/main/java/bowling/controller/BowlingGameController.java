package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Frames frames = new Frames();
        List<String> scores = frames.initFrames();

        String playerName = inputView.inputPlayerName();
        resultView.outputScores(playerName, scores);

        executeGame(frames, playerName);
        inputView.scannerClose();
    }

    private static void executeGame(final Frames frames, final String playerName) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        int frameNumber = 1;
        while (frameNumber <= Frames.TOTAL_FRAME_NUMBER) {
            int downPinNumber = inputView.inputNFrameThrow(frameNumber);
            frames.throwBall(frameNumber, Score.from(downPinNumber));
            if (frames.isNext(frameNumber)) {
                frameNumber++;
            }
            resultView.outputScores(playerName, frames.results());
        }
    }
}
