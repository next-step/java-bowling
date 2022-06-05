package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private static final int UNIT_INDEX = 1;
    private static final int START_INDEX = 0;

    private final List<BowlingGame> bowlingGames;
    private int playerIndex;

    public BowlingGames(List<BowlingGame> bowlingGames) {
        validateBowlingGames(bowlingGames);
        this.bowlingGames = bowlingGames;
        this.playerIndex = 0;
    }

    private void validateBowlingGames(List<BowlingGame> bowlingGames) {
        if (bowlingGames == null) {
            throw new IllegalArgumentException("볼링 게임 리스트는 null 일 수 없습니다.");
        }
    }

    public static BowlingGames initialize(List<Player> players) {
        List<BowlingGame> bowlingGames = players.stream().map(BowlingGame::create).collect(Collectors.toList());
        return new BowlingGames(bowlingGames);
    }

    public void playBowlingGame(Pins hitPins) {
        BowlingGame bowlingGame = bowlingGames.get(playerIndex);
        bowlingGame.bowl(hitPins);
        if (bowlingGame.isCurrentFrameEnd()) {
            bowlingGame.updateToNextFrameNumber();
            updatePlayerIndex();
        }
    }

    private void updatePlayerIndex() {
        playerIndex++;
        if (playerNumber() < playerIndex + UNIT_INDEX) {
            playerIndex = START_INDEX;
        }
    }

    private int playerNumber() {
        return bowlingGames.size();
    }

    public boolean isCurrentPlayerName(String playerName) {
        return bowlingGames.get(playerIndex).playerName().equals(playerName);
    }

    public boolean isRunning() {
        return bowlingGames.stream().anyMatch(BowlingGame::isRunning);
    }

    public String currentPlayerName() {
        return bowlingGames.get(playerIndex).playerName();
    }

    public List<BowlingGame> bowlingGames() {
        return bowlingGames;
    }
}