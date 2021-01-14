package bowling.domain;


import bowling.domain.frame.Frame;

import java.util.Objects;

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

    public int countFrames() {
        return frames.count();
    }

    public Frame searchFrame(int index){
        return frames.searchFrame(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player) &&
                Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, frames);
    }

    public int askRunningFrameIndex() {
        return frames.askRunningFrameIndex();
    }

}
