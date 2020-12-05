package bowling.domain.bowl;

import bowling.domain.Pin;
import bowling.domain.PlayerStatus;
import bowling.dto.PlayerStatusDto;

public class Bowl {
    private final PlayerStatus status = new PlayerStatus();
    private BowlState state = ReadyBowlState.getInstance();

    void setState(BowlState state) {
        this.state = state;
    }

    public void addPin(Pin pin) {
        status.addPin(pin);
    }

    Pin getLastPin() {
        return status.getLastPin();
    }

    public boolean isPlayable(int frameNumber) {
        return state.isPlayable(this, frameNumber);
    }

    public PlayerStatusDto exportPlayerStatusDto() {
        return status.exportPlayerStatusDto();
    }
}
