package bowling.controller;

import bowling.view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView.inputPlayerNames();



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
