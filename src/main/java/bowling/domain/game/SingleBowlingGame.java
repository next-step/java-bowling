package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class SingleBowlingGame {

    private final Player player;
    private final Frames frames;


    private SingleBowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static SingleBowlingGame of(String playerName, Frames frames) {
        Player player = new Player(playerName);
        return new SingleBowlingGame(player, frames);
    }
}
