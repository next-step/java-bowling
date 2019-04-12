package domain.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerNames {
    private final List<PlayerName> names = new ArrayList<>();

    public PlayerNames(List<String> names) {
        for(String name : names) {
            this.names.add(new PlayerName(name));
        }
    }

    public List<PlayerName> getNames() {
        return names;
    }
}