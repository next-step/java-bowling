package bowling;

import java.util.List;

public class ScoreBoard {

    private final Player player;
    private final Frames frames;

    private ScoreBoard(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static ScoreBoard of(Player player, int totalFrames) {
        Frames frames = Frames.of(totalFrames);
        return new ScoreBoard(player, frames);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public void bowl(Pin pin) {
        frames.bowl(pin);
    }

    public boolean isFinished() {
        return frames.isFinished();
    }
}
