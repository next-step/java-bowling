package bowling.step2.domain.process;

import bowling.step2.domain.frame.Frames;
import bowling.step2.domain.player.Player;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame of(String inputName) {
        return new BowlingGame(Player.of(inputName), new Frames());
    }

    public boolean isGameOver() {
        return frames.isEnd();
    }

    public BowlingGame play(int pitch){
        frames.progress(pitch);
        return this;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getFrameNo() {
        return frames.getFramesSize();
    }

    public String viewFrames() {
        return frames.toString();
    }

    public Frames getFrames() {
        return frames;
    }
}
