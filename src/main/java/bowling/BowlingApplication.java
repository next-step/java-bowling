package bowling;

import bowling.game.Frames;
import bowling.game.ScoreBoard;
import bowling.player.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Arrays;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputName();
        Player player = new Player(name);

        ScoreBoard scoreBoard = new ScoreBoard(Arrays.asList(player));
        Frames frames = scoreBoard.findByPlayer(player);

        while (!frames.isEndAllFrames()) {
            int frameNumber = frames.getCurrentFrameNumber();
            frames.bowlCurrentFrame(InputView.inputPinCount(frameNumber));

            OutputView.printScoreBoard(player, frames);
        }
    }
}
