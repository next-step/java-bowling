package bowling;

import bowling.Frame.Frame;
import bowling.Frame.Frames;
import bowling.pin.Pins;
import bowling.player.Player;

import java.util.List;

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
        List<Frame> frameList = frames.getFrames();
        return frames.getFrames().get(frameList.size()-1);
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }
}
