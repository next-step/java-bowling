package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        OutputView.outputDefaultFrame(playerName);

        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (!bowlingGame.isGameOver()) {
            bowlingGame.addNextFrame();
            play(bowlingGame);
        }

    }

    private static void play(BowlingGame bowlingGame) {
        int frameIndex = bowlingGame.currentPlayFrameIndex();
        Player player = bowlingGame.getPlayer();
        while (bowlingGame.isCurrentFramePlayable()) {
            int point = InputView.inputScore(frameIndex);
            bowlingGame.writePoint(point);
            OutputView.outputFrames(bowlingGame.getFrames(), player);
        }
    }
}
