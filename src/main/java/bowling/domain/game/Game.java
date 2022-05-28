package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;

import java.util.stream.IntStream;

public class Game {

    private static final int FIRST_PITCH = 0;
    private static final int LIMIT = 2;

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

    public void playing() {
        IntStream.range(FIRST_PITCH, LIMIT)
                .forEach(i -> this.bowling());
    }

    private void bowling() {
        if (isNext()) {
            int count = InputView.inputCount(this.frames.currentRound());
            this.frames.bowling(count);
        }
    }

    public boolean isNext() {
        return this.frames.isNext();
    }

    public void print() {
        this.frames.print();
    }
}
