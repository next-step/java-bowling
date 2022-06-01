package bowling.domain;

import java.util.List;

import static bowling.util.Const.*;

public class Player {
    private final PlayerName playerName;
    private final Frames frames;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.frames = new Frames();
    }

    public Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static int pitch(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public void plays() {
        this.frames.plays(this);
    }

    public List<Score> scores() {
        return this.frames.scores();
    }

    public List<Subtotal> subtotals(){
        return this.frames.subtotals();
    }

    public PlayerName name() {
        return this.playerName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName=" + playerName +
                ", frames=" + frames +
                '}';
    }

    public Frames frames() {
        return this.frames;
    }
}
