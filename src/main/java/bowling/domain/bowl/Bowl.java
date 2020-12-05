package bowling.domain.bowl;

import bowling.domain.PlayerStatus;
import bowling.domain.Roll;
import bowling.dto.PlayerStatusDto;

public class Bowl {
    private final PlayerStatus status = new PlayerStatus();
    private BowlState state = ReadyBowlState.getInstance();

    void setState(BowlState state) {
        this.state = state;
    }

    public void addRoll(Roll roll) {
        status.addRoll(roll);
    }

    int getLastCountOfPins() {
        return status.getLastCountOfPins();
    }

    public boolean isPlayable(int frameNo) {
        return state.isPlayable(this, frameNo);
    }

    public PlayerStatusDto exportPlayerStatusDto() {
        return status.exportPlayerStatusDto();
    }
}
