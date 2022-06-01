package bowling;

import static bowling.ui.InputCui.inputParticipants;
import static bowling.ui.OutputCui.drawStatus;

import bowling.controller.BowlingGameController;

public class MainApp {

    public static void main(String[] args) {
        BowlingGameController bowlingGame = new BowlingGameController();

        Games games = bowlingGame.createGames(inputParticipants());
        drawStatus(games.get());

        play(games);
    }

    private static void play(Games games) {
        while (!games.isAllDone()) {
            games.playAll();
        }
    }

}
