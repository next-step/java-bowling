package bowling.bowl;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.player.Player;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    private BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public BowlingGame(Player player) {
        this(player, new Frames());
    }

    public void pitch(Pins pins){
        frames.pitch(pins);
    }

    public boolean hasNext(){
        return getCurFrame().hasNext();
    }

    public int getFameIndex(){
        return getCurFrame().getIndex();
    }

    public Frame getCurFrame(){
        return frames.getCurFrame();
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }
}
