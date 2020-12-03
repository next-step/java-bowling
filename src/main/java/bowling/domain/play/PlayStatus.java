package bowling.domain.play;

import bowling.domain.PlayerStatus;
import bowling.domain.Roll;
import bowling.dto.PlayerStatusDto;

public class PlayStatus {
    private final PlayerStatus status = new PlayerStatus();
    private PlayState state = InitialPlayState.getInstance();

    void setState(PlayState state) {
        this.state = state;
    }

    public void addRoll(Roll roll) {
        status.addRoll(roll);
    }

    int getLastCountOfPins() {
        return status.getLastCountOfPins();
    }

    public boolean isContinue(int frameNo) {
        return state.isContinue(this, frameNo);
    }

    public PlayerStatusDto exportPlayerStatusDto() {
        return status.exportPlayerStatusDto();
    }
}
