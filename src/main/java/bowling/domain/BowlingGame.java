package bowling.domain;


import bowling.domain.frame.Frame;

public class BowlingGame {

    private Player player;
    private Frames frames;

    private BowlingGame(Player player, Frames frames){
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame of(Player player, Frames frames){
        return new BowlingGame(player, frames);
    }

    public Player getPlayer(){
        return player;
    }

    public Frames getFrames(){
        return frames;
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int countFrames() {
        return frames.count();
    }

    public Frame searchFrame(int index){
        return frames.searchFrame(index);
    }

}
