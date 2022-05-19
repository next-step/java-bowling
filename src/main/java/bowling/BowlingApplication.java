package bowling;

import bowling.domain.PlayerName;
import bowling.frame.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 9;

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
        lastFrame(playerName, frames, inputView, resultView);
    }

    private static void oneToNineFrame(PlayerName playerName, Frames frames, InputView inputView, ResultView resultView) {
        for (int round = MIN_ROUND; round < MAX_ROUND; round++) {
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

    private static void lastFrame(PlayerName playerName, Frames frames, InputView inputView, ResultView resultView) {
        Round lastRound = Round.from(MAX_ROUND);
        Frame lastFrame = frames.findFrameByRound(lastRound);

        int firstShoot = inputView.inputShootScore(lastRound);
        lastFrame.shoot(ShootScore.from(firstShoot));
        resultView.printFrameBoard(playerName, frames);

        int secondShoot = inputView.inputShootScore(lastRound);
        lastFrame.shoot(ShootScore.from(secondShoot));
        resultView.printFrameBoard(playerName, frames);

        if (!lastFrame.isEnd()) {
            int bonusShoot = inputView.inputShootScore(lastRound);
            lastFrame.shoot(ShootScore.from(bonusShoot));
            resultView.printFrameBoard(playerName, frames);
        }
    }
}
