package bowling.controller.dto;

import java.util.List;

public class GameStatus {
    List<PlayerFrameStatus> playerFrameStatuses;

    public GameStatus(List<PlayerFrameStatus> playerFrameStatuses) {
        this.playerFrameStatuses = playerFrameStatuses;
    }

    public PlayerFrameStatus get(int index) {
        return playerFrameStatuses.get(index);
    }

    public int size() {
        return playerFrameStatuses.size();
    }
}
