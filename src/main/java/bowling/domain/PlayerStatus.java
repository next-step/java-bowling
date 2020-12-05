package bowling.domain;

import bowling.dto.PlayerStatusDto;

public class PlayerStatus {
    private final Pins pins = new Pins();
    private final Board board = new Board();

    public void addPin(Pin pin) {
        pins.add(pin);
        board.update(pins);
    }

    public int getLastCountOfPins() {
        return pins.getLast().getCountOfPins();
    }

    public PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(pins.exportPinsDto(), board.exportBoardDto());
    }
}
