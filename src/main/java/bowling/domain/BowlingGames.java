package bowling.domain;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    public BowlingGames(final List<BowlingGame> bowlingGames) {
        this.bowlingGames = new ArrayList<>(bowlingGames);
    }

    public static BowlingGames from(final Players players) {
        List<BowlingGame> merge = players.getPlayers()
                .stream()
                .map(BowlingGame::new)
                .collect(Collectors.toList());

        return new BowlingGames(merge);
    }

    public void playPlayerGame(final Player player, final int pinCount) {
        BowlingGame playerGame = findPlayerGame(player);
        playerGame.play(pinCount);
    }

    private BowlingGame findPlayerGame(final Player player) {
        return bowlingGames.stream()
                .filter(game -> game.equalPlayer(player))
                .findFirst()
                .orElseThrow(BowlingException::new);
    }

    public boolean isMoreThrowable(final Player player, final int frameNumber) {
        BowlingGame playerGame = findPlayerGame(player);
        return !playerGame.isFrameFinish(frameNumber);
    }

    public boolean isAllPlayerLastFrameFinish(final int frameNumber) {
        return bowlingGames.stream()
                .anyMatch(game -> game.isFrameFinish(frameNumber) == false) ? false : true;
    }

    public boolean isAllEnd() {
        return bowlingGames.stream()
                .anyMatch(game -> game.isEnd() == false) ? false : true;
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGames games = (BowlingGames) o;
        return Objects.equals(bowlingGames, games.bowlingGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingGames);
    }
}
