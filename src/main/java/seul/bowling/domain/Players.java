package seul.bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Players {
    private List<Player> players;

    public Players(List<String> names) {
        this.players = new ArrayList<>();
        names.stream().forEach(name -> players.add(new Player(name)));
    }

    public Frame play(String name, int clearPin) {
        Player player = getPlayer(name);

        return player.play(clearPin);
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.equalsName(name))
                .findAny().get();
    }

    public static Players of(List<String> names) {
        return new Players(names);
    }
}



