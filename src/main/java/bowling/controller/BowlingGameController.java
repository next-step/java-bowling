package bowling.controller;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.Player;
import bowling.domain.Results;
import bowling.domain.frame.Frames;
import bowling.domain.score.PureScores;
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
        List<Integer> cumulatedScores = new ArrayList<>();
        PureScores pureScores = PureScores.from(frames, cumulatedScores);

        while (!frames.isFinish()) {
            int downPinNumber = inputView.inputNFrameThrow(frames.frameNumber());
            pureScores.addScore(Score.from(downPinNumber));
            frames.throwBalls(downPinNumber);
            resultView.outputScores(playerName, frames.results());
            cumulatedScores = pureScores.getCumulativeScores(frames, cumulatedScores);
            resultView.outputCumulativeScores(cumulatedScores);
        }
    }
}
