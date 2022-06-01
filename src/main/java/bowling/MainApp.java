package bowling;

import static bowling.ui.InputCui.inputParticipants;
import static bowling.ui.OutputCui.drawStatus;

import bowling.controller.BowlingGameController;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        BowlingGameController bowlingGame = new BowlingGameController();

        final List<String> names = inputParticipants();
        Games games = bowlingGame.createFrames(names);
        drawStatus(games.get());

        play(games);
    }

    private static void play(Games games) {
        while (!games.isAllDone()) {
            games.playAll();
        }
    }

}
