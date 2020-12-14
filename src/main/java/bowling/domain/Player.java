package bowling.domain;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Pin;
import bowling.dto.AskPinDto;
import bowling.dto.PlayerDto;
import bowling.exception.PlayerException;

public class Player {
    private final String name;
    private final Bowl bowl = new Bowl();

    public Player(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw new PlayerException("플레이어 이름은 3개의 영어 문자여야 합니다.");
        }
        this.name = name;
    }

    public boolean isPlayable() {
        return bowl.isPlayable();
    }

    public void addPin(Pin pin) {
        bowl.addPin(pin);
    }

    public void updateBowl() {
        bowl.update();
    }

    PlayerDto exportPlayerDto() {
        return new PlayerDto(name, bowl.exportBowlDto());
    }

    public AskPinDto exportAskPinDto() {
        return new AskPinDto(name, bowl.getFrameNumber());
    }
}
