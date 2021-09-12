package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.frame.FramesGroup;
import bowling.domain.player.Player;

import java.util.List;
import java.util.Map;

public class BowlingGame {

    private final FramesGroup framesGroup;

    public BowlingGame(final List<String> playerNames) {
        this.framesGroup = FramesGroup.of(playerNames);
    }

    public void input(final int knockDownNumber) {
        framesGroup.inputNumber(knockDownNumber);
    }

    public boolean isGameOver() {
        return framesGroup.isFinished();
    }

    public String getCurrentTurn() {
        return framesGroup.nextPlayer().getName();
    }

    public Map<Player, Frames> getFramesGroup() {
        return framesGroup.getFramesGroup();
    }
}
