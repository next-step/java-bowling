package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BowlingGame {

    private List<Player> players;
    private ScoreSheets scoreSheets;

    public BowlingGame(String... playerNames) {
        this(Arrays.stream(playerNames).map(name -> new Player(name)).collect(Collectors.toList()));
    }

    public BowlingGame(List<Player> players) {
        this.players = players;
        this.scoreSheets = new ScoreSheets(players);
    }

    public void start() {
        start(game -> {});
    }

    public void start(Consumer<BowlingGame> actionBeforeStart) {
        actionBeforeStart.accept(this);
    }

    public List<ScoreSheetReader> getReaders(){
        return scoreSheets.getReaders();
    }

    public boolean isEnd() {
        return scoreSheets.isAllMarked();
    }

    public FrameSet nextFrameSet() {
        return scoreSheets.nextFrameSet();
    }
}
