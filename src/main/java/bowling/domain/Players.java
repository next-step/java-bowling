package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        names.forEach((name) -> players.add(new Player(name)));
    }

    public Records getRecords() {
        List<Record> recordList = new ArrayList<>();
        players.forEach((player) -> recordList.add(new Record(player)));
        return new Records(recordList);
    }
}
