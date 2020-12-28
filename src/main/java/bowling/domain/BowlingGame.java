package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BowlingGame {

    private List<Player> players;
    private ScoreBoards scoreBoards;

    public BowlingGame(String... playerNames) {
        this(Arrays.stream(playerNames).map(name -> new Player(name)).collect(Collectors.toList()));
    }

    public BowlingGame(List<Player> players) {
        this.players = players;
        this.scoreBoards = new ScoreBoards(players);
    }

    public FrameSet start() {
        return start(game -> {});
    }

    public FrameSet start(Consumer<BowlingGame> actionBeforeStart) {
        actionBeforeStart.accept(this);
        return scoreBoards.nextFrameSet();
    }

    public boolean hasNextFrameSet() {
        return scoreBoards.hasNextFrame();
    }

    public FrameSet nextFrameSet() {
        return null;
    }
}
