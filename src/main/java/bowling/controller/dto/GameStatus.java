package bowling.controller.dto;

import java.util.List;

public class GameStatus {
    List<FrameStatus> frameStatuses;
    String playerName;

    public GameStatus(List<FrameStatus> frameStatuses, String playerName) {
        this.playerName = playerName;
        this.frameStatuses = frameStatuses;
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
