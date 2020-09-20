package bowling.player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Player> getPlayerInfo() {
        return Collections.unmodifiableMap(playerInfo);
    }

    public int size() {
        return playerInfo.size();
    }

}
