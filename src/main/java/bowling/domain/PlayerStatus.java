package bowling.domain;

import bowling.dto.PlayerStatusDto;

class PlayerStatus {
    private final Rolls rolls = new Rolls();
    private final Board board = new Board();

    void addRoll(Roll roll) {
        rolls.add(roll);
        board.update(rolls);
    }

    PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(rolls.exportRollsDto(), board.exportBoardDto());
    }
}
