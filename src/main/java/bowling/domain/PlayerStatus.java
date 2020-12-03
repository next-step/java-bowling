package bowling.domain;

import bowling.dto.PlayerStatusDto;

public class PlayerStatus {
    private final Rolls rolls = new Rolls();
    private final Board board = new Board();

    public void addRoll(Roll roll) {
        rolls.add(roll);
        board.update(rolls);
    }

    public int getLastCountOfPins() {
        return rolls.getLast().getCountOfPins();
    }

    public PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(rolls.exportRollsDto(), board.exportBoardDto());
    }
}
