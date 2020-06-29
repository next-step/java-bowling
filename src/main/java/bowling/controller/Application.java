package bowling.controller;

import bowling.domain.game.MultiBowlingGame;
import bowling.domain.score.PitchScore;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(InputView.inputPlayerNames());

        while (multiBowlingGame.hasNextTurn()) {
            PitchScore pitchScore = PitchScore.valueOf(InputView.inputPitchScore(multiBowlingGame));
            multiBowlingGame.bowl(pitchScore);
            OutputView.printTest(multiBowlingGame);
            multiBowlingGame.moveToNextFrame();
        }



/*        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();
        OutputView.printDefaultScoreBoard(player);

        while (frames.hasNextTurn()) {
            PitchScore pitchScore = PitchScore.valueOf(InputView.inputPitchScore(frames));
            frames.bowl(pitchScore);
            OutputView.printBowlingScoreBoard(player, frames);
            frames.moveToNextFrame();
        }*/
    }
}
