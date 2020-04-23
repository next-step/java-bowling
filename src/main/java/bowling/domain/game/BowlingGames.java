package bowling.domain.game;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.point.Point;

import java.util.ArrayList;
import java.util.List;

public class BowlingGames {
    private static final int FIRST_GAME_INDEX = 0;

    private List<BowlingGame> bowlingGames;

    public BowlingGames(Players players) {
        this.bowlingGames = setUpGames(players);
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    public void throwBall(Point point) {
        nextGame().throwBall(point);
    }

    public boolean isEnd() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isEnd);
    }

    public BowlingGame nextGame() {
        if (isRoundAllDone()) {
            readyToNext();
        }
        return bowlingGames.stream()
                .filter(bowlingGame -> !bowlingGame.isCurrentRoundDone())
                .findFirst()
                .orElseGet(() -> bowlingGames.get(FIRST_GAME_INDEX));
    }

    public void readyToNext() {
        bowlingGames.stream()
                .forEach(bowlingGame -> bowlingGame.makeNextFrame());
    }

    private boolean isRoundAllDone() {
        return bowlingGames.stream()
                .allMatch(bowlingGames -> bowlingGames.isCurrentRoundDone());
    }

    private List<BowlingGame> setUpGames(Players players) {
        List<BowlingGame> baseBowlingGames = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            baseBowlingGames.add(new BowlingGame(player));
        }
        return baseBowlingGames;
    }
}
