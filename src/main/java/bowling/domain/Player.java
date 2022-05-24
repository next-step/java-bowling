package bowling.domain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.util.Const.*;

public class Player {
    private final PlayerName playerName;
    private final Scores scores;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.scores = new Scores(IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList()));
    }

    public static int pitch(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public PlayFrames plays() {
        return this.scores.plays(this.playerName);
    }

    public String getPlayFrame() {
        return this.scores.playFrame(this.playerName);
    }
}
