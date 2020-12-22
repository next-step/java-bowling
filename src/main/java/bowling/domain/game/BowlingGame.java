package bowling.domain.game;

import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    private BowlingGame(Frames frames, Player player) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame start(Player player) {
        Frames frames = Frames.create();
        return new BowlingGame(frames, player);
    }

    public boolean roll(int numberOfDownPin) {
        return this.frames.roll(numberOfDownPin);
    }

    public String getPlayer() {
        return player.toString();
    }

    public List<FrameResult> getResult() {
        return this.frames.getFrameResults();
    }

    public boolean isFinished() {
        return this.frames.isFinished();
    }
}
