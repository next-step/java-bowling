package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.PitchScore;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();
        OutputView.printDefaultScoreBoard(player);

        while (frames.hasNextTurn()) {
            PitchScore pitchScore = PitchScore.valueOf(InputView.inputPitch(frames));
            frames.bowl(pitchScore);
            OutputView.printBowlingScoreBoard(player, frames);
            frames.moveToNextFrame();
        }
    }
}
