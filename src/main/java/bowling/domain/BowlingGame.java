package bowling.domain;

import bowling.domain.player.Player;
import bowling.exception.message.ErrorMessage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGame {

    private static final int FIRST_GAME_SET_NUMBER = 0;

    private final List<GameSet> gameSets;
    private int currentGameSetNumber;

    private BowlingGame(final List<String> names) {
        validateNames(names);

        this.gameSets = names.stream()
                .map(String::trim)
                .map(Player::of)
                .map(GameSet::of)
                .collect(Collectors.toList());
        this.currentGameSetNumber = 0;
    }

    public static BowlingGame of(final List<String> names) {
        return new BowlingGame(names);
    }

    private void validateNames(final List<String> names) {
        if (Objects.isNull(names)|| names.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VALUE);
        }
    }

    public boolean isGameOver() {
        return gameSets.stream()
                .allMatch(GameSet::isGameOver);
    }

    public String getPlayerName() {
        return this.currentGameSet().getPlayerName();
    }

    public void playOfCurrentGameSet(final int hitCount) {
        this.currentGameSet().play(hitCount);
    }

    private GameSet currentGameSet() {
        int index = currentGameSetNumber;

        return gameSets.get(index);
    }

    public void turnOverGameSet() {
        if (!isTurnOver()) {
            return;
        }

        this.currentGameSetNumber++;

        if (this.currentGameSetNumber == gameSets.size()) {
            this.currentGameSetNumber = FIRST_GAME_SET_NUMBER;
        }
    }

    private boolean isTurnOver() {
        return this.currentGameSet().isTurnOver();
    }

    public List<GameSet> getGameSets() {
        return gameSets;
    }
}
