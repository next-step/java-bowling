package bowling.domain.player;


import bowling.domain.frame.Frames;

public class Bowler {
    private final Player player;
    private final Frames frames;

    public Bowler(String name) {
        player = Player.of(name);
        frames = Frames.of();
    }

    public static Bowler of(String name) {
        return new Bowler(name);
    }

    public String getName() {
        return player.getName();
    }

}
