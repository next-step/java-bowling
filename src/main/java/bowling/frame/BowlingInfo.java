package bowling.frame;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BowlingInfo {

    private Map<String, Frames> playerInfo;

    private BowlingInfo(Map<String, Frames> playerInfo) {
        this.playerInfo = playerInfo;
    }

    public static BowlingInfo info() {
        return new BowlingInfo(new HashMap<>());
    }

    public Frames put(String name, Frames frames) {
        return this.playerInfo.put(name, frames);
    }

    public Map<String, Frames> getPlayerInfo() {
        return Collections.unmodifiableMap(playerInfo);
    }

    public int size() {
        return playerInfo.size();
    }

}
