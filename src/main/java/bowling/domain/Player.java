package bowling.domain;

import java.util.List;

import static bowling.util.Const.*;

public class Player {
    private final PlayerName playerName;
    private final Scores scores;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.scores = new Scores();
    }

    public static int pitch(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public void plays() {
        this.scores.plays(this);
    }

    public List<Score> scores() {
        return this.scores.scores();
    }

    public PlayerName name() {
        return this.playerName;
    }
}
