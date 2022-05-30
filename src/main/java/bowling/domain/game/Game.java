package bowling.domain.game;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class Game {

    private static final int FIRST_ROUND = 1;

    private final Player player;
    private final Frames frames;

    private Game(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Game init(String name) {
        Player player = Player.of(name);
        Frames frames = Frames.init();

        return new Game(player, frames);
    }

    public void bowling(int pins) {
        this.frames.bowling(pins);
    }

    public boolean isNext() {
        return this.frames.isNext();
    }

    public int currentRound() {
        if (this.frames.isEmpty()) {
            return FIRST_ROUND;
        }

        Frame currentFrame = this.frames.currentFrame();
        int round = currentFrame.round();

        if (currentFrame.isFinishBowling() || currentFrame.isFinalFrame()) {
            ++round;
        }

        return round;
    }

    public Player player() {
        return this.player;
    }

    public Frames frames() {
        return this.frames;
    }
}
