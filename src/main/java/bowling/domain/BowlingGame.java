package bowling.domain;

import bowling.dto.BowlingGameDto;
import bowling.dto.PlayerDto;

import java.util.List;

public class BowlingGame {
    private final Players players;
    private int currentFrameNo = 1;

    private BowlingGame(PlayerNames playerNames) {
        players = Players.of(playerNames);
    }

    public static BowlingGame init(PlayerNames playerNames) {
        return new BowlingGame(playerNames);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        Player player = players.getCurrentPlayer(currentFrameNo);
        player.setKnockDownPins(knockDownPins);
        if (players.isAllPlayerEndFrame(currentFrameNo)) {
            currentFrameNo++;
        }
    }

    public Player getCurrentPlayer() {
        return players.getCurrentPlayer(currentFrameNo);
    }

    public boolean isEnd() {
        return players.isAllPlayerEndGame();
    }

    public BowlingGameDto convertToDto() {
        List<PlayerDto> playerDtos = players.convertToDto();
        return BowlingGameDto.of(playerDtos);
    }

    @Override
    public String toString() {
        return "BowlingGame{" +
                "players=" + players +
                ", currentFrameNo=" + currentFrameNo +
                '}';
    }
}
