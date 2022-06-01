package bowling;

import static bowling.ui.InputCui.inputHitCount;
import static bowling.ui.OutputCui.drawStatus;

import bowling.domain.Game;
import java.util.List;

public class Games {

    private List<Game> games;

    public Games(List<Game> games) {
        this.games = games;
    }


    public List<Game> get() {
        return games;
    }

    public boolean isAllDone() {
        return games.stream()
            .allMatch(Game::isEndGame);
    }

    public void playAll() {
        for (Game game : games) {
            play(game);
        }
    }

    private void play(Game game) {
        boolean isDoneTurn = false;
        while (!isDoneTurn) {
            int hitCount = inputHitCount(game.name());
            isDoneTurn = game.throwBall(hitCount);
            drawStatus(games);
        }
    }

}
