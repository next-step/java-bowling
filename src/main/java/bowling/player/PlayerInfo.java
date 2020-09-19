package bowling.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerInfo {

    private Map<String, Player> playerInfo;

    private PlayerInfo(Map<String, Player> playerInfo) {
        this.playerInfo = playerInfo;
    }

    public static PlayerInfo info() {
        return new PlayerInfo(new HashMap<>());
    }

    public Player put(String name, Player player) {
        return this.playerInfo.put(name, player);
    }

    public int size() {
        return playerInfo.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInfo that = (PlayerInfo) o;
        return Objects.equals(playerInfo, that.playerInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerInfo);
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "playerInfo=" + playerInfo +
                '}';
    }
}
