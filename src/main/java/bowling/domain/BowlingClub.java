package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BowlingClub {

    private final List<BowlingGame> games = new ArrayList<>();

    public BowlingClub(List<String> names) {
        for (String name : names) {
            games.add(new BowlingGame(name));
        }
    }

    public List<BowlingGame> games() {
        return Collections.unmodifiableList(games);
    }

    public boolean isNotEnd() {
        return games.get(games.size() - 1).isNotEnd();
    }

    public String nameOfPlayerForThisTurn() {
        return playingFrame()
                .map(BowlingGame::playerName)
                .orElseGet(this::nameOfFirstPlayer);
    }

    private Optional<BowlingGame> playingFrame() {
        return games().stream()
                .filter(game -> !game.currentFrameIsEnd())
                .findFirst();
    }

    private String nameOfFirstPlayer() {
        return games().get(0).playerName();
    }

    public void roll(int fallenPin) {
        nextFrame();
        playingFrame().ifPresent(game -> game.roll(fallenPin));
    }

    private void nextFrame() {
        if (allPlayerRolled()) {
            games().forEach(BowlingGame::nextFrame);
        }
    }

    private boolean allPlayerRolled() {
        return games().stream()
                .allMatch(BowlingGame::currentFrameIsEnd);
    }
}
