package bowling.domain.bowlingboard;

import bowling.domain.Player;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;

public class PlayerBowlingBoard {

    private final Player player;
    private BowlingBoard bowlingBoard;

    public PlayerBowlingBoard(Player player, BowlingBoard bowlingBoard) {
        this.player = player;
        this.bowlingBoard = bowlingBoard;
    }

    public static PlayerBowlingBoard of(String playerName, int position) {
        return new PlayerBowlingBoard(Player.of(playerName, position), BowlingBoard.of());
    }

    public Player player() {
        return player;
    }

    public BowlingBoard bowlingBoard() {
        return bowlingBoard;
    }

    public void pitching(Point point) {
        bowlingBoard = bowlingBoard.pitching(point);
    }

    public boolean isSamePlayer(Player currentThrowPlayer) {
        return player.equals(currentThrowPlayer);
    }


    public boolean isSamePosition(int playerPosition) {
        return player.isSamePosition(playerPosition);
    }

    public Round round() {
        return bowlingBoard.round();
    }
}
