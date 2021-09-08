package bowling.controller;

import java.util.ArrayList;

import bowling.domain.Player;
import bowling.domain.Results;
import bowling.domain.frame.Frames;
import bowling.domain.score.AccumulatedScores;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Frames frames = Frames.from(new ArrayList<>());
        Player player = Player.from(inputView.inputPlayerName());

        resultView.outputScores(player.name(), Results.from(frames).results());
        resultView.outputCumulativeScores(new ArrayList<>());

        executeGame(frames, player.name());
        inputView.scannerClose();
    }

    private static void executeGame(final Frames frames, final String playerName) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        AccumulatedScores accumulatedScores = AccumulatedScores.from(frames);
        while (!frames.isFinish()) {
            int downPinNumber = inputView.inputNFrameThrow(frames.frameNumber());
            accumulatedScores.addScore(Score.from(downPinNumber));
            frames.throwBalls(downPinNumber);
            resultView.outputScores(playerName, Results.from(frames).results());
            resultView.outputCumulativeScores(accumulatedScores.getCumulativeScores(frames));
        }
    }
}
