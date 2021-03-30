package bowling.dto;

import bowling.domain.Player;

import java.util.List;

public class ScoreBoard {
    private PlayerDto playerDto;

    public ScoreBoard(Player player) {
        this.playerDto = new PlayerDto(player);
    }

    public PlayerDto playerResult() {
        return playerDto;
    }
}
