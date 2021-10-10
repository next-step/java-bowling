package step4.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayersFrames {
    Map<String, Frames> playersFrames;

    public PlayersFrames() {
        this.playersFrames = new HashMap<>();
    }

    public void put(String nameOfPerson, Frames frames) {
        playersFrames.put(nameOfPerson, frames);
    }

    public Frames ofFrames(String player) {
        return playersFrames.get(player);
    }

    public Set<String> playerSet() {
        return playersFrames.keySet();
    }
}
