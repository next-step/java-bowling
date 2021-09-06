package bowling.controller;

import java.util.ArrayList;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Frames frames = Frames.from(new ArrayList<>());
        Player player = Player.from(inputView.inputPlayerName());

        resultView.outputScores(player.name(), frames.results());
        executeGame(frames, player.name());
        inputView.scannerClose();
    }

    private static void executeGame(final Frames frames, final String playerName) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        int frameNumber = 1;
        while (frameNumber <= Frames.TOTAL_FRAME_NUMBER) {
            int downPinNumber = inputView.inputNFrameThrow(frameNumber);
            frames.throwBalls(downPinNumber);
            frameNumber = frames.nextFrameNumber();
            resultView.outputScores(playerName, frames.results());
        }
    }
}
