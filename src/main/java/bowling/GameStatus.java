package bowling;

import bowling.domain.Frame;
import bowling.domain.Scoring;

public class GameStatus {
    private GameService game;

    public GameStatus(GameService game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "|  " + game.getPlayerName() + " " +
               "| " + getFirstFrameStatus() + "    |      |      |      |      |      |      |      |      |      |";
    }

    private String getFirstFrameStatus() {
        Frame firstFrame = game.getFirstFrame();
        return firstFrame.getScoring().map(scoring -> {
            if (scoring == Scoring.STRIKE) {
                return "X";
            }
            return null;
        }).orElse("");
    }
}
