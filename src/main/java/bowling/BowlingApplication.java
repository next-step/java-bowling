package bowling;

import bowling.domain.PlayerName;
import bowling.frame.Frames;
import bowling.frame.Round;
import bowling.frame.ShootScore;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 10;
    private static final int PREVIOUS = -1;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Frames frames = Frames.create();

        PlayerName playerName = PlayerName.from(inputView.inputPlayerName());

        resultView.printFrameBoard(playerName, frames);

        startBowlingGame(playerName, frames, inputView, resultView);
    }

    private static void startBowlingGame(PlayerName playerName, Frames frames, InputView inputView, ResultView resultView) {
        oneToNineFrame(playerName, frames, inputView, resultView);
    }

    private static void oneToNineFrame(PlayerName playerName, Frames frames, InputView inputView, ResultView resultView) {
        for (int round = MIN_ROUND; round < MAX_ROUND + PREVIOUS; round++) {
            Round currentRound = Round.from(round);

            int firstShoot = inputView.inputShootScore(currentRound);
            frames.shoot(currentRound, ShootScore.from(firstShoot));
            resultView.printFrameBoard(playerName, frames);

            if (frames.isRoundEnd(currentRound)) {
                continue;
            }

            int secondShoot = inputView.inputShootScore(currentRound);
            frames.shoot(currentRound, ShootScore.from(secondShoot));
            resultView.printFrameBoard(playerName, frames);
        }
    }
}
