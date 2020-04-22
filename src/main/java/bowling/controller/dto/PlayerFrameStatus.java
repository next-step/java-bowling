package bowling.controller.dto;

import java.util.List;

public class PlayerFrameStatus {
    List<FrameStatus> frameStatuses;
    String playerName;

    public PlayerFrameStatus(
            List<FrameStatus> frameStatuses, String playerName) {
        this.frameStatuses = frameStatuses;
        this.playerName = playerName;
    }

    public FrameStatus getFrameStatus(int i) {
        return frameStatuses.get(i);
    }

    public int getFrameStatusesSize() {
        return frameStatuses.size();
    }

    public String getPlayerName() {
        return playerName;
    }
}
