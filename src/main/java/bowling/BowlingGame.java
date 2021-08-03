package bowling;

import bowling.domain.player.BowlingPlayer;
import bowling.domain.player.BowlingPlayers;
import bowling.dto.BowlingPlayerDto;

import java.util.List;

public class BowlingGame {
    private BowlingPlayer currentPlayer;
    private final BowlingPlayers bowlingPlayers;

    public BowlingGame(List<String> names) {
        bowlingPlayers = BowlingPlayers.init(names);
        currentPlayer = bowlingPlayers.firstPlayer();
    }

    public static BowlingGame init(List<String> names) {
        return new BowlingGame(names);
    }

    public void play(int numberOfDownedPins) {
        currentPlayer.play(numberOfDownedPins);

        rotatePlayer();
    }

    private void rotatePlayer() {
        if (currentPlayer.isTurnOver()) {
            currentPlayer = bowlingPlayers.rotatePlayer(currentPlayer);
        }
    }

    public List<BowlingPlayerDto> playerDtos() {
        return bowlingPlayers.playerDtos();
    }

    public boolean isGameEnd() {
        return bowlingPlayers.isAllPlayersEnd();
    }

    public BowlingPlayerDto currentPlayerDto() {
        return BowlingPlayerDto.from(currentPlayer);
    }
}
